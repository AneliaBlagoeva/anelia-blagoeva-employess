package com.sirma.longesteamperiod;

import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sirma.longesteamperiod.domain.OverlappedEmployeePair;
import com.sirma.longesteamperiod.domain.Record;
import com.sirma.longesteamperiod.service.PeriodCalculatorService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("main-scene.fxml")
public class RecordController {

	@FXML
	private Label path;
	@FXML
	private TableView resultView;

	private PeriodCalculatorService serv;
	List<Record> parsedRecords = new ArrayList<>();

	@Autowired
	public RecordController(PeriodCalculatorService serv) {
		this.serv = serv;
	}

	public void calculatePeriod(ActionEvent actionEvent) {
		List<OverlappedEmployeePair> resultSet = serv.calculate(parsedRecords);
		resultView.getItems().addAll(resultSet);

	}

	public void browse(ActionEvent actionEvent) throws FileNotFoundException {
		List<String[]> parsedFileResult = new ArrayList<>();

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));

		Stage mainStage = (Stage) resultView.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(mainStage);
		if (selectedFile != null) {
			this.path.setText(selectedFile.toString());
		}

		try {
			parsedFileResult = this.serv.parseFile(selectedFile);
			parsedRecords = this.serv.parseData(parsedFileResult);
		} catch (IllegalArgumentException e) {
			this.path.setText(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			this.path.setText("Error while reading file!");
			e.printStackTrace();
		}

	}

}
