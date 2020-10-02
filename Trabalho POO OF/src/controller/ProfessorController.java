package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.ProfessorDao;
import entidade.Professor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ProfessorController extends Application{

	 	@FXML
	    private TextField tf_nome_professor;

	    @FXML
	    private TextField tf_email_professor;

	    @FXML
	    private Button btn_adicionar_prof;

	    @FXML
	    private Button btn_excluir_prof;

	    @FXML
	    private TextArea ta_lista_professor;

	    @FXML
	    private Button btn_edita_prof;
	    
	    @FXML
	    private Button btn_buscar_prof;
	    
	    @FXML
	    private DatePicker datePickerEmissao;

	    @FXML
	    private Label label_id;
	    
	    @FXML
	    private Label label_label;

	    @FXML
	    private TextField tx_busca_id;
	    
	    @FXML
	    private TextField tx_Cpf;
	    
	    @FXML
	    void buscarProfessor(ActionEvent event) {

	    	String idString = tx_busca_id.getText();
			Professor pessoa = null;
			if (!idString.equals("")) {
				try {
					int id = Integer.valueOf(idString);
					pessoa = new ProfessorDao().findByID(id);
				} catch (Exception e) {

				}

				if (pessoa != null) {
					label_id.setVisible(true);
					label_label.setVisible(true);
					label_label.setText(pessoa.getId() + "");
					tf_nome_professor.setText(pessoa.getNome());
					tf_email_professor.setText(pessoa.getEmail());
					tx_Cpf.setText(pessoa.getPeso() + "");
					datePickerEmissao.setValue(pessoa.getData_admissao().toLocalDate());
				}

			}
			tx_busca_id.clear();
	    	
	    }
	    
	    @FXML
	    void inserirProfessor(ActionEvent event) {
    	
	    	Professor professor = pegaDados();
	    	limpaCampos();
	    	int qtde = new ProfessorDao().inserir(professor);
	    	listarProfessor();
	    	System.out.println(qtde);
    	
	    }
	    
	    @FXML
	    void AlterarProfessor(ActionEvent event) {
	
	    	Professor professor = pegaDadosID();
			limpaCampos();
			int qtde = new ProfessorDao().alterar(professor);
			listarProfessor();
	    	
	    }
	    
	    @FXML
	    void excluiProfessor(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Deletar Professor");
	    	alert.setContentText("Tem certeza que deseja deletar o Professor?");
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		Professor Professor = pegaDadosID();
	    		int qtde = new ProfessorDao().deletar(Professor.getId());
	    		listarProfessor();
	    	}
	    	limpaCampos();
	    	}
	    	
	    
	    private Professor pegaDados() {
			return new Professor(tf_nome_professor.getText(), tf_email_professor.getText(), Double.valueOf(tx_Cpf.getText()), Date.valueOf(datePickerEmissao.getValue()));
		}
		
		private Professor pegaDadosID() {
			return new Professor(Integer.valueOf(label_label.getText()), tf_nome_professor.getText(), tf_email_professor.getText(), Double.valueOf(tx_Cpf.getText()), Date.valueOf(datePickerEmissao.getValue()));
		}
	
		private void limpaCampos() {
			tx_Cpf.clear();
			tf_email_professor.clear();
			tf_nome_professor.clear();
			tf_nome_professor.requestFocus();
			label_id.setVisible(false);
			label_label.setVisible(false);
			label_label.setText("");
			datePickerEmissao.setValue(null);
		}
		
	    
	    private void listarProfessor() {
	    	ta_lista_professor.clear();
			List<Professor> listaPessoa = new ProfessorDao().listAll();
			listaPessoa.forEach(pessoa -> {
				ta_lista_professor.appendText(pessoa.toString() + "\n");
			});
		}
	    
	    
		public void execute() {
			launch();
		}
	
		@Override
		public void start(Stage stage) {
	
			try {
				AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("CENTRAL.fxml"));
				Scene sc = new Scene(pane);
				stage.setScene(sc);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}
		
	
	}
	
