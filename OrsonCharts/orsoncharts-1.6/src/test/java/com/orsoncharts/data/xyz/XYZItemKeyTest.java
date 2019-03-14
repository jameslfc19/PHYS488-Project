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

package com.orsoncharts.data.xyz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.orsoncharts.TestUtils;

/**
 * Tests for the {@link XYZItemKey} class.
 */
public class XYZItemKeyTest {
    
    @Test
    public void testEquals() {
        XYZItemKey<String> k1 = new XYZItemKey<String>("S1", 1);
        XYZItemKey<String> k2 = new XYZItemKey<String>("S1", 1);
        assertTrue(k1.equals(k2));
        assertFalse(k1.equals(null));
        
        k1 = new XYZItemKey<String>("S2", 1);
        assertFalse(k1.equals(k2));
        k2 = new XYZItemKey<String>("S2", 1);
        assertTrue(k1.equals(k2));
        
        k1 = new XYZItemKey<String>("S2", 2);
        assertFalse(k1.equals(k2));
        k2 = new XYZItemKey<String>("S2", 2);
        assertTrue(k1.equals(k2));
    }

    /**
     * Check for serialization support.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSerialization() {
        XYZItemKey<String> k1 = new XYZItemKey<String>("S1", 1);
        XYZItemKey<String> k2 = (XYZItemKey<String>) TestUtils.serialized(k1);
        assertEquals(k1, k2);
    }
    
}
