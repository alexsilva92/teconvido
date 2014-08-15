<?php

/* 
 * Copyright 2014 Alejandro Silva <alexsilva792@gmail.com>.
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

?>
<div class="col-xs-4">
    <div class="panel panel-disable panel-success">

        <div class="panel-heading">
            <h3 class="panel-title">Coche</h3>
        </div>

        <div class="panel-body">
            <div class="panel panel-disable panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Marca y Modelo</h3>
                </div>
                <div class="panel-body ui-widge">
                    <input class="form-control input-car input-disable" name="travel_car" type=text list=cars placeholder="Coche" required>
                    <?php include $root.Module::$ListCar ?>
                </div>
            </div>
            
            <div class="panel panel-disable panel-success">
                
                <div class="panel-heading">
                    <h3 class="panel-title">Plazas</h3>
                </div>
                
                <div class="panel-body">
                    <input class="form-control input-space input-disable" name="travel_space" type="number" value="3" min="1" placeholder="Plazas"  required>
                </div>
            </div>

        </div>

        <div class="panel-footer">
            <input class="input-disable" name="travel_terms" type=checkbox required> Declaro estar en posesión de un permiso de conducir y de un seguro vigente.<br>
        </div>

    </div>
</div>
