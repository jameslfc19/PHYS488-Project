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

package com.orsoncharts.graphics3d;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Some tests for the methods in {@link Utils3D}.
 */
public class Utils3DTest {
   
    private static final double EPSILON = 0.00000001;

    @Test
    public void testLength() {
        assertEquals(1, Utils3D.length(Point3D.UNIT_X), EPSILON);
        assertEquals(1, Utils3D.length(Point3D.UNIT_Y), EPSILON);
        assertEquals(1, Utils3D.length(Point3D.UNIT_Z), EPSILON);
        assertEquals(0, Utils3D.length(Point3D.ORIGIN), EPSILON);
    }
    
    @Test
    public void testAngle() {
        assertEquals(Math.PI / 2, Utils3D.angle(Point3D.UNIT_X, Point3D.UNIT_Y),
                EPSILON);
    }
}