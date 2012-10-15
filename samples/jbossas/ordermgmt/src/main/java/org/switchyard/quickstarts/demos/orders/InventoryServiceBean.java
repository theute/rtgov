/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2010-2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more 
 * details. You should have received a copy of the GNU Lesser General Public 
 * License, v.2.1 along with this distribution; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  
 * 02110-1301, USA.
 */

package org.switchyard.quickstarts.demos.orders;

import java.util.HashMap;
import java.util.Map;

import org.switchyard.component.bean.Service;

@Service(InventoryService.class)
public class InventoryServiceBean implements InventoryService {

    private final Map<String, Item> _inventory = new HashMap<String, Item>();
    
    public InventoryServiceBean() {
        Item butter = new Item()
            .setItemId("BUTTER")
            .setName("Not Parkay")
            .setQuantity(1000)
            .setUnitPrice(1.25);
        _inventory.put(butter.getItemId(), butter);
        
        Item jam = new Item()
            .setItemId("JAM")
            .setName("Strawberry Jam")
            .setQuantity(500)
            .setUnitPrice(2.40);
        _inventory.put(jam.getItemId(), jam);
    }

    @Override
    public Item lookupItem(String itemId) throws ItemNotFoundException {
        Item item = _inventory.get(itemId);
        if (item == null) {
            throw new ItemNotFoundException("We don't got any " + itemId);
        }
        
        if (itemId.equals("JAM")) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return item;
    }
}
