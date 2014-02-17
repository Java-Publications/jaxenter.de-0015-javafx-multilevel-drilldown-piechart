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

import org.rapidpm.demo.jaxenter.blog0015.MapAggregator;

import java.util.List;


/**
 * User: Sven Ruppert
 * Date: 02.09.13
 * Time: 16:01
 */
public abstract class DrillDownPieChartMapAggregator<T> implements MapAggregator<T, String> {

    /**
     * Calculate the Value for the PiChart.Data based on the aggregated values
     *
     * @param aggregatedValues
     * @return
     */
    public abstract double aggregateValue(List<T> aggregatedValues);

    public abstract String getLevelName();

    private DrillDownPieChartMapAggregator<T> nextLevelAggregator;
    private DrillDownPieChartMapAggregator<T> parentLevelAggregator;

    public DrillDownPieChartMapAggregator<T> getNextLevelAggregator() {
        return nextLevelAggregator;
    }

    public DrillDownPieChartMapAggregator<T> getParentLevelAggregator() {
        return parentLevelAggregator;
    }

    public void setNextLevelAggregator(DrillDownPieChartMapAggregator<T> nextLevelAggregator) {
        this.nextLevelAggregator = nextLevelAggregator;
        this.nextLevelAggregator.parentLevelAggregator = this;
    }

    public boolean isLastOne() {
        return nextLevelAggregator == null;
    }

    public boolean isFirstOne() {
        return parentLevelAggregator == null;
    }

}
