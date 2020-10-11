package com.sirma.longesteamperiod;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.sirma.longesteamperiod.RecordController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

public class JavaFxApplication extends Application {

	private ConfigurableApplicationContext applicationContext;

	@Override
	public void init() {
		String[] args = getParameters().getRaw().toArray(new String[0]);

		this.applicationContext = new SpringApplicationBuilder().sources(LongesteamperiodApplication.class).run(args);
	}

	@Override
	public void start(Stage stage) {
		FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
		Parent root = fxWeaver.loadView(RecordController.class);
		root.setStyle("-fx-background-color: #FFFFFF;");

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setWidth(500);
		stage.setHeight(800);

		stage.show();

	}

	@Override
	public void stop() {
		this.applicationContext.close();
		Platform.exit();
	}

}