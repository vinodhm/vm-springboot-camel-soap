/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package com.vm.demo;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vm.demo.vo.CustomerService;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;
    
    
    //NOTE THE VALUE OF cxf.path in application.properties this leads to 
    //the URL of the soap service being of the form /service/CustomerServicePort
    
    @Bean(name="CustomerServiceProcessor")
    public CustomerServiceProcessor getProcessor(){
    	return new CustomerServiceProcessor();
    }

    @Bean
    public CxfEndpoint customerServiceEndpoint() {
    	
    	CxfEndpoint cxfEndpoint = new CxfEndpoint();
    	cxfEndpoint.setAddress("/CustomerServicePort");
    	cxfEndpoint.setServiceNameString("s:customer:customerServiceService");
    	cxfEndpoint.setServiceClass(CustomerService.class);
    	cxfEndpoint.setBus(bus);
        return cxfEndpoint;
    }
    
    @Bean
    public CustomerService customerService()
    {
    	return new CustomerServiceImpl();
    }
}
