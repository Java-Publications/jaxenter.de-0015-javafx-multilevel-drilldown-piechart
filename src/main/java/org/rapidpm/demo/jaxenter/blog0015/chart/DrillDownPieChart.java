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

package org.rapidpm.demo.jaxenter.blog0015.chart;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseButton;

import static javafx.collections.FXCollections.observableArrayList;
import static javafx.scene.input.MouseButton.*;

/**
 * User: Sven Ruppert
 * Date: 02.09.13
 * Time: 15:12
 */
public abstract class DrillDownPieChart<T> extends PieChart {

    private ObservableList<PieChart.Data> rootDataList;
    private ObservableList<T> dataValueList;

    private DrillDownPieChartMapAggregator<T> rootAggregator;
    private DrillDownPieChartMapAggregator<T> activeAggregator;

    protected DrillDownPieChart() {
        this.rootDataList = observableArrayList();
        this.dataValueList = observableArrayList();
    }

    public void init() {
        rootDataList.clear();
        this.setTitle("");
        activeAggregator = rootAggregator;
        setVisible(true);
        aggregateNextLevel(dataValueList);
    }

    private void aggregateNextLevel(final ObservableList<T> dataValueList) {
        ObservableList<Data> levelDataList = observableArrayList();
        setData(levelDataList);

        if (activeAggregator == null) {
            init();
        } else {
            setTitle("..." + activeAggregator.getLevelName() + "...");
            final Map<String, List<T>> aggregatedMap = activeAggregator.aggregate(dataValueList);
            aggregatedMap.forEach((key, value) -> {
                final ObservableList<T> valuesForNextLevel = observableArrayList(value);
                final Data data = new Data(key, activeAggregator.aggregateValue(valuesForNextLevel));
                levelDataList.add(data);
                final Node dataNode = data.getNode();
                dataNode.setOnMouseClicked(mouseEvent -> {
                    final MouseButton mouseButton = mouseEvent.getButton();
                    if (mouseButton.equals(SECONDARY)) {
                        //level back
                        activeAggregator = activeAggregator.getParentLevelAggregator();
                    } else {
                        activeAggregator = activeAggregator.getNextLevelAggregator();
                    }
                    aggregateNextLevel(valuesForNextLevel);
                });
            });
        }
    }

    public void setRootAggregator(DrillDownPieChartMapAggregator<T> rootAggregator) {
        this.rootAggregator = rootAggregator;
    }

    public ObservableList<T> getDataValueList() {
        return dataValueList;
    }
}
