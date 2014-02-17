/*
 * Copyright [2013] [www.rapidpm.org / Sven Ruppert (sven.ruppert@rapidpm.org)]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * User: Sven Ruppert
 * Date: 02.09.13
 * Time: 15:33
 */
public class DrillDownPieChartTest extends Application {

    public static void main(String[] args) {
          Application.launch(DrillDownPieChartTest.class);
    }

    private DemoDataBuilder dataBuilder = new DemoDataBuilder();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("DrillDownPieChartTest");

        FXMLLoader loader = new FXMLLoader();
        final URL resource = getClass().getClassLoader()
                .getResource("DrillDownPieChartPane.fxml");
        loader.setLocation(resource);
        loader.setRoot(new DrillDownPieChartPane());
        loader.setControllerFactory(aClass -> new DrillDownPieChartPaneController());

        final DrillDownPieChartPane pieChartPane = loader.load();
        final DrillDownPieChartPaneController controller = loader.getController();
        final DemoDrillDownPieChart pieChart = controller.getPiechart();
        pieChart.getDataValueList().addAll(dataBuilder.create());
        pieChart.init();
        stage.setScene(new Scene(pieChartPane, 512, 512));
        stage.show();
    }
}
