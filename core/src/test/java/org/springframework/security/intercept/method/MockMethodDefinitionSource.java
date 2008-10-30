/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.intercept.method;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.SecurityConfig;


/**
 *
 * @author Ben Alex
 * @version $Id$
 */
public class MockMethodDefinitionSource implements MethodDefinitionSource {
    //~ Instance fields ================================================================================================

    private List list;
    private boolean returnACollection;

    //~ Constructors ===================================================================================================

    public MockMethodDefinitionSource(boolean includeInvalidAttributes, boolean returnACollectionWhenRequested) {
        returnACollection = returnACollectionWhenRequested;
        list = new Vector();

        List<? extends ConfigAttribute> def1 = SecurityConfig.createList("MOCK_LOWER");
        list.add(def1);

        if (includeInvalidAttributes) {
            List<? extends ConfigAttribute> def2 = SecurityConfig.createList("MOCK_LOWER","INVALID_ATTRIBUTE");
            list.add(def2);
        }

        List<? extends ConfigAttribute> def3 = SecurityConfig.createList("MOCK_UPPER", "RUN_AS_");
        list.add(def3);

        if (includeInvalidAttributes) {
            List<? extends ConfigAttribute> def4 = SecurityConfig.createList("MOCK_SOMETHING", "ANOTHER_INVALID");
            list.add(def4);
        }
    }

    //~ Methods ========================================================================================================

    public Collection<List<? extends ConfigAttribute>> getAllConfigAttributes() {
        if (returnACollection) {
            return list;
        } else {
            return null;
        }
    }

    public List<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        throw new UnsupportedOperationException("mock method not implemented");
    }

    public List<ConfigAttribute> getAttributes(Method method, Class targetClass) {
        throw new UnsupportedOperationException("mock method not implemented");
    }

    public boolean supports(Class clazz) {
        return (MethodInvocation.class.isAssignableFrom(clazz) || JoinPoint.class.isAssignableFrom(clazz));
    }

}
