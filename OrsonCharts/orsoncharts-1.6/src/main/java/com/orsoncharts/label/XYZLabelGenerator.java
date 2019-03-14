/* ===========================================================
 * Orson Charts : a 3D chart library for the Java(tm) platform
 * ===========================================================
 * 
 * (C)opyright 2013-2016, by Object Refinery Limited.  All rights reserved.
 * 
 * http://www.object-refinery.com/orsoncharts/index.html
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 * 
 * If you do not wish to be bound by the terms of the GPL, an alternative
 * commercial license can be purchased.  For details, please see visit the
 * Orson Charts home page:
 * 
 * http://www.object-refinery.com/orsoncharts/index.html
 * 
 */

package com.orsoncharts.label;

import com.orsoncharts.data.xyz.XYZDataset;

/**
 * A label generator for XYZ charts, used to create labels for the chart 
 * legend.  The ({@link StandardXYZLabelGenerator}) class provides the default 
 * implementation.
 * 
 * @since 1.2
 */
public interface XYZLabelGenerator {

    /**
     * Generates a label for one series in a {@link XYZDataset}.
     * 
     * @param <S> The type of the series keys in the dataset.
     * @param dataset  the dataset ({@code null} not permitted).
     * @param seriesKey  the series key ({@code null} not permitted).
     *
     * @return The series label (possibly {@code null}).
     */
    public <S extends Comparable<S>> String generateSeriesLabel(
            XYZDataset<S> dataset, S seriesKey);

}
