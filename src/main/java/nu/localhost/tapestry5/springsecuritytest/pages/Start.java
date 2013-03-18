/*
 * Copyright 2007 Robin Helgelin
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

package nu.localhost.tapestry5.springsecuritytest.pages;

import nu.localhost.tapestry5.springsecurity.services.LogoutService;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.security.access.annotation.Secured;


/**
 * @author Robin Helgelin
 */
public class Start
{
    @Inject
    private LogoutService logoutService;
    
    @Persist
    private int counter;
    
    @Secured("ROLE_TELLER")
    public void onActionFromCounter() {
        counter++;
    }
    
    @OnEvent(component = "logout")
    public void doLogout() {
        logoutService.logout();
    }
    
    public int getCounter() {
        return counter;
    }
}