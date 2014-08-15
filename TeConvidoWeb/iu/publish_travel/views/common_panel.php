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

<div class="col-xs-8">

    <div class="panel panel-success">

        <div class="panel-heading">
            <h3 class="panel-title">Anunciante</h3>
        </div>

        <div class="panel-body">
            <div class="col-xs-4">
                <input name="travel_advertiser" id="disabled-false"  type="radio" value="driver" checked="checked"> Conductor<br> 
            </div>
            <div class="col-xs-4">
                <input name="travel_advertiser" id="disabled-true" type="radio"  value="passenger"> Pasajero<br>
            </div>
        </div>

    </div>

    <div class="panel panel-success">

        <div class="panel-heading">
            <h3 class="panel-title">Viaje</h3>
        </div>

        <div class="panel-body">
            
            <div class="ui-widget">
                <input class="form-control input-town input-origin" name="travel_origin" type="text" placeholder="Origen" required><br>
            </div>

            <div class="ui-widget">
                <input class="form-control input-town input-destination" name="travel_destination" type="text"  placeholder="Destino" required>
            </div>
            
        </div>

    </div>

    <div class="panel panel-success">

        <div class="panel-heading">
            <h3 class="panel-title">Fecha de salida</h3>
        </div>

        <div class="panel-body">
            <div class="row">
                <div class="col-xs-6">
                    <input class="form-control input-date" name="travel_date" value="<?php echo date('Y-m-d');?>" type="date" placeholder="Fecha" required>
                </div>

                <div class="col-xs-3">
                    <input class="form-control" name="travel_time" type="time" placeholder="Hora" required>
                </div>
            </div>
        </div>

    </div>

</div>