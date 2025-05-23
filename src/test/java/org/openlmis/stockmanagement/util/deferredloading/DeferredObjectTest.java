/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2017 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms
 * of the GNU Affero General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details. You should have received a copy of
 * the GNU Affero General Public License along with this program. If not, see
 * http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org.
 */

package org.openlmis.stockmanagement.util.deferredloading;

import static org.junit.Assert.assertEquals;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class DeferredObjectTest {
  private static final String SOME_KEY = "abc123";
  private static final String SOME_VALUE = "test";

  @Test(expected = NullPointerException.class)
  public void shouldThrowWhenNullKeyIsUsed() {
    new DeferredObject<>(null);
  }

  @Test(expected = IllegalStateException.class)
  public void shouldThrowWhenAccessedUninitiated() {
    final DeferredObject<String, String> uninitiated = new DeferredObject<>(SOME_KEY);
    uninitiated.get();
  }

  @Test
  public void shouldReturnValueWhenInitiated() {
    final DeferredObject<String, String> initiated = new DeferredObject<>(SOME_KEY);
    initiated.set(SOME_VALUE);

    assertEquals(SOME_VALUE, initiated.get());
  }

  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(DeferredObject.class).withIgnoredFields("initiated", "value")
        .usingGetClass().verify();
  }
}
