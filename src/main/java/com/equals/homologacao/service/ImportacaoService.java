package com.equals.homologacao.service;

import com.equals.homologacao.model.Empresa;
import com.equals.homologacao.model.Extrato;
import com.equals.homologacao.model.TipoPagamento;
import com.equals.homologacao.model.Transacao;
import com.equals.homologacao.repository.EmpresaRepository;
import com.equals.homologacao.repository.ExtratoRepository;
import com.equals.homologacao.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImportacaoService {

    private final EmpresaRepository empresaRepository;
    private final ExtratoRepository extratoRepository;
    private final TransacaoRepository transacaoRepository;

    /**
     * Método responsável pela leitura dos dados das linhas do arquivo recebido
     * para cada entidade relacionada, com base no tipo explicito em cada linha
     *
     * @param arquivo arquivo importado
     * @return Retorna uma mensagem de sucesso na importação dos dados
     */
    @Transactional
    public String importarDadosTransacoes(MultipartFile arquivo) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {

            String linha;
            Empresa empresa;
            Extrato extrato = null;

            while ((linha = reader.readLine()) != null) {
                char tipo = linha.charAt(0);

                switch (tipo) {
                    case '0' -> { // Header
                        empresa = criarEmpresa(linha);
                        extrato = criarExtrato(linha, empresa);

                    }
                    case '1' -> { // Detalhe (transação)
                        Transacao novaTransacao = criarTransacao(linha, extrato);
                        if (novaTransacao != null) {
                            Optional<Transacao> transacaoExistente = transacaoRepository.findByCodigoTransacao(novaTransacao.getCodigoTransacao());
                            if (transacaoExistente.isEmpty()) {
                                transacaoRepository.save(novaTransacao);
                            } else {
                                System.out.println("Transação duplicada ignorada: " + novaTransacao.getCodigoTransacao());
                            }
                        }
                    }
                    case '9' -> { // Trailer
                        if (extrato != null) {
                            extrato.setTotalRegistros(parseInt(linha.substring(1, 12)));
                            extratoRepository.save(extrato);
                        }
                    }
                }
            }

            return "Extrato importado com sucesso!";

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo do extrato", e);
        }
    }

    /**
     * Método responsável pela alocação dos dados lidos para um objeto de Empresa
     *
     * @param linha linha específica do arquivo importado
     * @return Retorna um objeto de Empresa
     */
    public Empresa criarEmpresa(String linha) {
        String numeroEstabelecimento = linha.substring(1, 11).trim();

        // Verifica se a empresa já existe no banco,
        // apenas caso não exista a nova empresa é salva no banco de dados
        return empresaRepository.findByNumeroEstabelecimento(numeroEstabelecimento)
                .orElseGet(() -> {
                    Empresa nova = new Empresa();
                    nova.setNumeroEstabelecimento(numeroEstabelecimento);
                    return empresaRepository.save(nova); // já salva se for nova
                });
    }

    /**
     * Método responsável pela alocação dos dados lidos para um objeto de Extrato
     *
     * @param linha   linha específica do arquivo importado
     * @param empresa objeto de Empresa para ser relacionada ao extrato
     * @return Retorna um objeto de Extrato
     */
    public Extrato criarExtrato(String linha, Empresa empresa) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Extrato e = new Extrato();

        e.setDataProcessamento(LocalDate.parse(linha.substring(11, 19), dateFormatter));
        e.setPeriodoInicial(LocalDate.parse(linha.substring(19, 27), dateFormatter));
        e.setPeriodoFinal(LocalDate.parse(linha.substring(27, 35), dateFormatter));
        e.setTipoExtrato(linha.substring(47, 49).trim());
        e.setEmpresa(empresa);

        extratoRepository.save(e);

        return e;
    }

    /**
     * Método responsável pela alocação dos dados lidos para um objeto de Transacao
     *
     * @param linha   linha específica do arquivo importado
     * @param extrato objeto de Extrato para ser relacionado à transação
     * @return Retorna um objeto de Transacao
     */
    public Transacao criarTransacao(String linha, Extrato extrato) throws IOException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        Transacao t = new Transacao();

        t.setDataInicioTransacao(LocalDate.parse(linha.substring(11, 19), dateFormatter));
        t.setDataEvento(LocalDate.parse(linha.substring(19, 27), dateFormatter));
        t.setHoraEvento(LocalTime.parse(linha.substring(27, 33), timeFormatter));
        t.setTipoEvento(linha.substring(33, 35).trim());
        t.setTipoTransacao(linha.substring(35, 37).trim());
        t.setNumeroSerieLeitor(linha.substring(37, 45).trim());
        t.setCodigoTransacao(linha.substring(45, 77).trim());
        t.setCodigoPedido(linha.substring(77, 97).trim());
        t.setValorTotal(parseLong(linha.substring(97, 110).trim()));
        t.setValorParcela(parseLong(linha.substring(110, 123).trim()));
        switch (linha.substring(123, 124).trim()) {
            case "A":
                t.setTipoPagamento(TipoPagamento.ANTECIPACAO_PARCELAS);
                break;
            case "U":
                t.setTipoPagamento(TipoPagamento.RECEBIMENTO_UNICO);
                break;
            case "M":
                t.setTipoPagamento(TipoPagamento.RECEBIMENTO_MULTIPLO);
                break;
            default:
                throw new IOException("Ausencia de tipo de pagamento!");
        }

        t.setPlano(parseInt(linha.substring(124, 126).trim()));
        t.setParcelaLiberada(parseInt(linha.substring(126, 128).trim()));
        t.setQuantidadeParcelas(parseInt(linha.substring(128, 130).trim()));
        t.setDataPagamento(LocalDate.parse(linha.substring(130, 138).trim(), dateFormatter));
        t.setTaxaParcelamentoComprador(parseLong(linha.substring(138, 151).trim()));
        t.setTarifaBoletoComprador(parseLong(linha.substring(151, 164).trim()));
        t.setValorOriginal(parseLong(linha.substring(164, 177).trim()));
        t.setTaxaParcelamentoVendedor(parseLong(linha.substring(177, 190).trim()));
        t.setTaxaIntermediacao(parseLong(linha.substring(190, 203).trim()));
        t.setTarifaIntermediacao(parseLong(linha.substring(203, 216).trim()));
        t.setTarifaBoletoVendedor(parseLong(linha.substring(216, 229).trim()));
        t.setRepasseAplicacao(parseLong(linha.substring(229, 242).trim()));
        t.setValorLiquidoTransacao(parseLong(linha.substring(242, 255).trim()));
        t.setStatusPagamento(linha.substring(255, 257).trim());
        t.setMeioPagamento(linha.substring(259, 261).trim());
        t.setBandeira(linha.substring(261, 291).trim());
        t.setCanalEntrada(linha.substring(291, 293).trim());
        t.setLeitor(linha.substring(293, 295).trim());
        t.setMeioCaptura(linha.substring(295, 297).trim());
        t.setNumeroLogico(linha.substring(297, 329).trim());
        t.setNsuDaTransacao(linha.substring(329, 340).trim());
        t.setCartaoBin(linha.substring(343, 349).trim());
        t.setCartaoHolder(linha.substring(349, 353).trim());
        t.setCodigoAutorizacao(linha.substring(353, 359).trim());
        t.setCodigoCv(linha.substring(359, 391).trim());
        t.setExtrato(extrato);

        return t;
    }

    /**
     * Método de conversão de String para Long
     */
    private Long parseLong(String campo) {
        try {
            return Long.parseLong(campo.trim());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    /**
     * Método para conversão de String para um Integer
     */
    private Integer parseInt(String campo) {
        try {
            return Integer.parseInt(campo.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
