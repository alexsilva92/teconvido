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

<script src="/public/jquery/jquery-1.10.2.min.js"></script>
<link href="/public/css/bootstrap.css" rel="stylesheet">
<link href="/public/css/teconvido.css" rel="stylesheet">

<script type="text/javascript">
	$(document).ready(function()
        {
		$("input[id=driver").change(function () {	 
                    document.getElementById('car').disabled = false;
                    document.getElementById('space').disabled = false;
                    document.getElementById('terms').disabled = false;
                    document.getElementById('panel-car').className = "panel panel-success";

		});
                $("input[id=passenger").change(function () {	 
                    document.getElementById('car').disabled = true;
                    document.getElementById('space').disabled = true;
                    document.getElementById('terms').disabled = true;
                    document.getElementById('panel-car').className = "panel panel-default";
		});
        });
</script>

<input id="driver" type="radio" name="type_advert" value="driver" checked="checked"> Conductor<br> 
<input id="passenger" type="radio" name="type_advert" value="passenger"> Pasajero<br> 

<div id="panel-car" class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">Coche</h3>
    </div>
    <div class="panel-body">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Marca y Modelo</h3>
            </div>
            <div class="panel-body">
                <input id="car" type=text list=cars placeholder="Coche" class="form-control">
                <datalist id=cars >
                    <option> Ford Mondeo 3853DMX
                    <option> Renault Megane 6411APR
                </datalist>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">Plazas</h3>
            </div>
            <div class="panel-body">
                <input id ="space" type="number" value="3" min="1" placeholder="Plazas" class="form-control" required>
            </div>


        </div>
    </div>
    <div class="panel-footer">
        <input id ="terms" type=checkbox name="terms"> Declaro estar en posesión de un permiso de conducir y de un seguro vigente.
    </div>
</div>