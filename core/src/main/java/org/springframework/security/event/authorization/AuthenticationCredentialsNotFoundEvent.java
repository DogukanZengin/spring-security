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

package org.springframework.security.event.authorization;

import java.util.List;

import org.springframework.security.AuthenticationCredentialsNotFoundException;
import org.springframework.security.ConfigAttribute;


/**
 * Indicates a secure object invocation failed because the <code>Authentication</code> could not be obtained from
 * the <code>SecurityContextHolder</code>.
 *
 * @author Ben Alex
 * @version $Id$
 */
public class AuthenticationCredentialsNotFoundEvent extends AbstractAuthorizationEvent {
    //~ Instance fields ================================================================================================

    private AuthenticationCredentialsNotFoundException credentialsNotFoundException;
    private List<ConfigAttribute> configAttribs;

    //~ Constructors ===================================================================================================

    /**
     * Construct the event.
     *
     * @param secureObject the secure object
     * @param configAttribs that apply to the secure object
     * @param credentialsNotFoundException exception returned to the caller (contains reason)
     *
     */
    public AuthenticationCredentialsNotFoundEvent(Object secureObject, List<ConfigAttribute> configAttribs,
            AuthenticationCredentialsNotFoundException credentialsNotFoundException) {
        super(secureObject);

        if ((configAttribs == null) || (credentialsNotFoundException == null)) {
            throw new IllegalArgumentException("All parameters are required and cannot be null");
        }

        this.configAttribs = configAttribs;
        this.credentialsNotFoundException = credentialsNotFoundException;
    }

    //~ Methods ========================================================================================================

    public List<ConfigAttribute> getConfigAttributes() {
        return configAttribs;
    }

    public AuthenticationCredentialsNotFoundException getCredentialsNotFoundException() {
        return credentialsNotFoundException;
    }
}
