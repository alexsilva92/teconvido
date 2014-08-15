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
<div class="panel panel-primary">
    <div class="panel-heading">
        <h2 class="panel-title">Buscar Viaje</h2>
    </div>
    <div class="panel-body">
        <form>
        <div class="row">                 
            <div class="col-xs-4 ui-widget">
                <input class="form-control input-town input-origin <?php Utilities::inputError(!$this -> correctOrigin);?>" 
                       id="origin" name="origin" type="text" placeholder="Origen" required>
                <?php setValueWithGet("origin"); ?>
            </div>
            <div class="col-xs-4 ui-widget">
                <input class="form-control input-town input-destination <?php Utilities::inputError(!$this -> correctDestination);?>" 
                       id="destination" name="destination" type="text" placeholder="Destino" required>
                <?php setValueWithGet("destination"); ?>
            </div>
            <div class="col-xs-4">
                <input class="form-control input-date <?php Utilities::inputError(!$this -> correctDate);?>" name="date" id="date"
                       type="date" placeholder="Fecha" required>
                <?php setValueWithGet("date"); ?>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-8">
                <div class="panel panel-info margin-top-15">

                    <div class="panel-heading">
                        <h3 class="panel-title">Buscar a</h3>
                    </div>

                    <div class="panel-body">
                        <div class="col-xs-4">
                            <input name="type_advertiser" type="radio" value="driver" checked="checked"> Conductores<br> 
                        </div>
                        <div class="col-xs-4">
                            <input id="passenger" name="type_advertiser" type="radio" value="passenger"> Pasajeros<br>
                        </div>
                        <?php setCheckedWithGet("type_advertiser","passenger"); ?>
                    </div>

                </div>
            </div>
            <div class="col-xs-4 margin-top-15">
                <button type="text" class="btn btn-primary btn-lg btn-block">Buscar</button>
            </div>
        </div>
        </form>   
        <?php $this -> showError()?>
    </div>
</div>