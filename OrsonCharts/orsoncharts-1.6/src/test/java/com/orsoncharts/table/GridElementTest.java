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

package com.orsoncharts.table;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.orsoncharts.TestUtils;

/**
 * Tests for the {@link GridElement} class.
 */
public class GridElementTest {
    
    @Test
    public void testEquals() {
        GridElement<String, String> e1 = new GridElement<String, String>();
        GridElement<String, String> e2 = new GridElement<String, String>();
        assertTrue(e1.equals(e2));
        
        e1.setElement(new TextElement("A"), "R1", "C1");
        assertFalse(e1.equals(e2));
        e2.setElement(new TextElement("A"), "R1", "C1");
        assertTrue(e1.equals(e2));
    }
    
    /**
     * A check for serialization.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSerialization() {
        GridElement<String, String> e1 = new GridElement<String, String>();
        GridElement<String, String> e2 = (GridElement) TestUtils.serialized(e1);
        assertTrue(e1.equals(e2));        
    }
}
