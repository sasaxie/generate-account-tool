package org.tron.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.tron.constant.Contant.Identity;
import org.tron.crypto.ECKey;
import org.tron.program.GenerateAccountTool;
import org.tron.utils.ByteArray;
import org.tron.utils.Utils;

public class GenerateAccountToolGUI extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Generate Account Tool");

    GridPane gridPane = new GridPane();

    gridPane.setAlignment(Pos.CENTER);
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.setPadding(new Insets(2, 10, 2, 10));

    // layout
    Label privateKeyLabel = new Label("Private Key:");
    gridPane.add(privateKeyLabel, 0, 0, 1, 1);

    TextField privateKeyTextField = new TextField();
    privateKeyTextField.setPrefColumnCount(32);
    privateKeyTextField.setEditable(false);
    gridPane.add(privateKeyTextField, 1, 0, 32, 1);

    Button copyPrivateKeyButton = new Button();
    ImageView copyImageView1 = new ImageView("copy.png");
    copyImageView1.setFitHeight(10);
    copyImageView1.setFitWidth(10);
    copyPrivateKeyButton.setGraphic(copyImageView1);
    copyPrivateKeyButton.setTooltip(new Tooltip("Copy private key"));
    copyPrivateKeyButton.setOnAction(event -> {
      String result = privateKeyTextField.getText();
      setSysClipboardText(result);
    });
    gridPane.add(copyPrivateKeyButton, 33, 0, 1, 1);

    Label publicKeyLabel = new Label("Public Key:");
    gridPane.add(publicKeyLabel, 0, 1, 1, 1);

    TextField publicKeyTextField = new TextField();
    publicKeyTextField.setEditable(false);
    gridPane.add(publicKeyTextField, 1, 1, 32, 1);

    Button copyPublicKeyButton = new Button();
    ImageView copyImageView2 = new ImageView("copy.png");
    copyImageView2.setFitHeight(10);
    copyImageView2.setFitWidth(10);
    copyPublicKeyButton.setGraphic(copyImageView2);
    copyPublicKeyButton.setTooltip(new Tooltip("Copy public key"));
    copyPublicKeyButton.setOnAction(event -> {
      String result = publicKeyTextField.getText();
      setSysClipboardText(result);
    });
    gridPane.add(copyPublicKeyButton, 33, 1, 1, 1);

    Label addressBase58Label = new Label("Address(Base58):");
    gridPane.add(addressBase58Label, 0, 2, 1, 1);

    TextField addressBase58Field = new TextField();
    addressBase58Field.setEditable(false);
    gridPane.add(addressBase58Field, 1, 2, 32, 1);

    Button copyAddressBase58Button = new Button();
    ImageView copyImageView3 = new ImageView("copy.png");
    copyImageView3.setFitHeight(10);
    copyImageView3.setFitWidth(10);
    copyAddressBase58Button.setGraphic(copyImageView3);
    copyAddressBase58Button.setTooltip(new Tooltip("Copy address(base58)"));
    copyAddressBase58Button.setOnAction(event -> {
      String result = addressBase58Field.getText();
      setSysClipboardText(result);
    });
    gridPane.add(copyAddressBase58Button, 33, 2, 1, 1);

    Label addressHexLabel = new Label("Address(Hex):");
    gridPane.add(addressHexLabel, 0, 3, 1, 1);

    TextField addressHexField = new TextField();
    addressHexField.setEditable(false);
    gridPane.add(addressHexField, 1, 3, 32, 1);

    Button copyAddressHexButton = new Button();
    ImageView copyImageView4 = new ImageView("copy.png");
    copyImageView4.setFitHeight(10);
    copyImageView4.setFitWidth(10);
    copyAddressHexButton.setGraphic(copyImageView4);
    copyAddressHexButton.setTooltip(new Tooltip("Copy address(hex)"));
    copyAddressHexButton.setOnAction(event -> {
      String result = addressHexField.getText();
      setSysClipboardText(result);
    });
    gridPane.add(copyAddressHexButton, 33, 3, 1, 1);

    Button generateButton = new Button("Generate");
    generateButton.setMaxWidth(Double.MAX_VALUE);
    GridPane.setHgrow(generateButton, Priority.ALWAYS);
    generateButton.setOnAction(event -> {
      ECKey ecKey = new ECKey(Utils.getRandom());

      privateKeyTextField.setText(ByteArray.toHexString(ecKey.getPrivKeyBytes()));
      publicKeyTextField.setText(ByteArray.toHexString(ecKey.getPubKey()));
      addressBase58Field.setText(Utils.encode58Check(ecKey.getAddress()));
      addressHexField.setText(ByteArray.toHexString(ecKey.getAddress()));
    });
    gridPane.add(generateButton, 0, 4, 34, 1);

    ToggleGroup netTypeToggleGroup = new ToggleGroup();

    RadioButton testnetRadioButton = new RadioButton("testnet");
    testnetRadioButton.setUserData("testnet");
    testnetRadioButton.setToggleGroup(netTypeToggleGroup);
    testnetRadioButton.setSelected(true);
    gridPane.add(testnetRadioButton, 18, 5, 1, 1);


    RadioButton mainnetRadioButton = new RadioButton("mainnet");
    mainnetRadioButton.setUserData("mainnet");
    mainnetRadioButton.setToggleGroup(netTypeToggleGroup);
    gridPane.add(mainnetRadioButton, 29, 5, 1, 1);

    if (testnetRadioButton.isSelected()) {
      GenerateAccountTool.addressPrefix = Identity.ADDRESS_PREFIX_TESTNET;
    } else {
      GenerateAccountTool.addressPrefix = Identity.ADDRESS_PREFIX_MAINNET;
    }

    netTypeToggleGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle newToggle) -> {
      if (netTypeToggleGroup.getSelectedToggle() != null) {
        if (netTypeToggleGroup.getSelectedToggle().getUserData().toString().equals("testnet")) {
          GenerateAccountTool.addressPrefix = Identity.ADDRESS_PREFIX_TESTNET;
        } else {
          GenerateAccountTool.addressPrefix = Identity.ADDRESS_PREFIX_MAINNET;
        }
      }
    });

    Scene scene = new Scene(gridPane, 600, 190);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public static void setSysClipboardText(String writeMe) {
    Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    Transferable tText = new StringSelection(writeMe);
    clip.setContents(tText, null);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
