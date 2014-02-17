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

import demo.model.TransientDemoDataRow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.rapidpm.demo.jaxenter.blog0015.chart.DrillDownPieChartMapAggregator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * User: Sven Ruppert
 * Date: 02.09.13
 * Time: 16:49
 */
public class DrillDownPieChartPaneController implements Initializable {

    @FXML DemoDrillDownPieChart piechart;

    private VornameAggregator vornameAggregator = new VornameAggregator();
    private NachnameAggregator nachnameAggregator = new NachnameAggregator();
    private DatumAggregator datumAggregator = new DatumAggregator();
    private BetragAggregator betragAggregator = new BetragAggregator();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        //setze Aggregatoren
        vornameAggregator.setNextLevelAggregator(nachnameAggregator);
        nachnameAggregator.setNextLevelAggregator(datumAggregator);
        datumAggregator.setNextLevelAggregator(betragAggregator);
        piechart.setRootAggregator(vornameAggregator);

        piechart.init();
    }

    public static class BetragAggregator
            extends DrillDownPieChartMapAggregator<TransientDemoDataRow> {

        @Override public double aggregateValue(List<TransientDemoDataRow> aggregatedValues) {
            return aggregatedValues
                    .stream()
                    .mapToDouble(TransientDemoDataRow::getBetrag)
                    .sum();
        }

        @Override public String getLevelName() {
            return "Betrag";
        }

        @Override
        public String getAggregationKey(TransientDemoDataRow value) {
            return value.getBetrag()+"";
        }
    }

    public static class DatumAggregator
            extends DrillDownPieChartMapAggregator<TransientDemoDataRow> {
        @Override public double aggregateValue(List<TransientDemoDataRow> aggregatedValues) {
            return aggregatedValues.size();
        }

        @Override public String getLevelName() {
            return "Datum";
        }

        @Override
        public String getAggregationKey(TransientDemoDataRow value) {
            return value.getDatum();
        }
    }

    public static class VornameAggregator
            extends DrillDownPieChartMapAggregator<TransientDemoDataRow> {
        @Override public double aggregateValue(List<TransientDemoDataRow> aggregatedValues) {
            return aggregatedValues.size();
        }

        @Override public String getLevelName() {
            return "Vorname";
        }
        @Override
        public String getAggregationKey(TransientDemoDataRow value) {
            return value.getVorname();
        }
    }

    public static class NachnameAggregator
            extends DrillDownPieChartMapAggregator<TransientDemoDataRow> {

        @Override
        public double aggregateValue(List<TransientDemoDataRow> aggregatedValues) {
            return aggregatedValues.size();
        }

        @Override public String getLevelName() {
            return "Nachname";
        }

        @Override
        public String getAggregationKey(TransientDemoDataRow value) {
            return value.getNachname();
        }
    }


    public DemoDrillDownPieChart getPiechart() {
        return piechart;
    }

    public void setPiechart(DemoDrillDownPieChart piechart) {
        this.piechart = piechart;
    }
}
