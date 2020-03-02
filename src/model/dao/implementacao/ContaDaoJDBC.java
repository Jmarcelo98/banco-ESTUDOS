package model.dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import banco_de_dados.BdExcecao;
import banco_de_dados.Conexao_banco_dados;
import model.dao.ContaDao;
import model.entities.Conta;

public class ContaDaoJDBC implements ContaDao {

	Connection conexao = null;
	PreparedStatement st = null;
	ResultSet rs = null;

	@Override
	public void inserir(Conta contaObj) {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement(
					"insert conta (NUMERO_CONTA, DIGITO_CONTA, LIMITE_CHEQUE_ESPECIAL, ID_TIPO_CONTA,  ID_STATUS_CONTA, ID_CLIENTE)"
							+ "values (?,?,?,?,?,?)");

			st.setInt(1, contaObj.getNumeroConta());
			st.setInt(2, contaObj.getDigitoConta());
			st.setDouble(3, contaObj.getLimiteCheque());
			st.setInt(4, contaObj.getIdTipoConta());
			st.setInt(5, contaObj.getIdStatusConta());
			st.setInt(6, contaObj.getIdCliente());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				JOptionPane.showMessageDialog(null, "ATENDENTE CADASTRADO COM SUCESSO", "CADASTRO ATENDENTE",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR O ATENDENTE", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

	@Override
	public void atualizar(Conta contaSimplesObj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletarPeloCPF(String CPF) {
		// TODO Auto-generated method stub

	}

	@Override
	public Conta procurarPeloCPF(String CPF) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conta> procurarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizandoStatusDeAcordoComIdCliente() {

		try {

			conexao = Conexao_banco_dados.abrirConexaoComOBanco();

			st = conexao.prepareStatement("select id_cliente from conta");

			rs = st.executeQuery();

			Integer id_cliente = rs.getInt("id_cliente");

			if (id_cliente != 1 || id_cliente != 2) {

				st = conexao.prepareStatement("update conta set id_status_conta = 2");
				st.executeUpdate();

			}

		} catch (SQLException e) {
			throw new BdExcecao(e.getMessage());
		} finally {
			Conexao_banco_dados.fecharStatement(st);
			Conexao_banco_dados.fecharConexaoComoBanco();
		}

	}

}