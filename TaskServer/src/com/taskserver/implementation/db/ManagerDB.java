/**
 *  Copyright 2014 Alejandro Silva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taskserver.implementation.db;

import com.taskserver.db.AbstractManagerDB;
import com.taskserver.implementation.common.ConstantDB;
import com.taskserver.implementation.common.ConstantDB.GetDB;
import com.taskserver.implementation.common.ConstantDB.InsertDB;
import com.taskserver.implementation.common.ConstantDB.RemoveDB;
import com.taskserver.implementation.common.ConstantDB.UpdateDB;
import com.utilities.cryptor.CryptorException;
import java.io.IOException;
import javax.xml.bind.JAXBException;

/**
 * ManagerDB.java
 * @author Alejandro Silva
 */
public class ManagerDB extends AbstractManagerDB<GetDB,InsertDB,UpdateDB,
RemoveDB> implements ConstantDB{
    
    public ManagerDB(String rutaConfig) throws JAXBException, IOException, 
    CryptorException, ClassNotFoundException{
        super(rutaConfig);
    }
    
    public ManagerDB() throws JAXBException, IOException, CryptorException,
    ClassNotFoundException{
        super();
    }

    @Override
    protected void initializeGets() {

    }

    @Override
    protected void initializeInserts() {

    }

    @Override
    protected void initializeUpdates() {

    }

    @Override
    protected void initializeRemoves() {

    }

    
}
