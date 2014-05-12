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
package com.teconvido.common;

/**
 * ConstantDB.java
 * @author Alejandro Silva
 */
public interface TeConvidoConstantDB {

    public enum GetDB{IS_CORRECT_LOGIN,GET_GCM_ID};
    
    public enum InsertDB{INSERT_USER,INSERT_C}
    
    public enum UpdateDB{UPDATE_GCM_CODE}
    
    public enum RemoveDB{}
}
