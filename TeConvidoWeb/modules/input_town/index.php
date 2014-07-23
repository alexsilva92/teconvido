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
include $root.Controller::$Client;

$client = new Client($host);
$towns = $client ->getTowns();
$tags = array();
foreach ($towns as $town) {
    array_push($tags, $town -> name); 
}
$tags = json_encode($tags);
echo $tags;
?>
<!--<script src="/public/jquery/jquery-1.10.2.min.js"></script>
<script src="/public/jquery/jquery-ui.js"></script>-->
<script>
$(function() {
    var tags = <?php echo $tags; ?>;
    $( "#origin" ).autocomplete({
      source: tags
    });
});
</script>

<script>
$(function() {
    var tags = <?php echo $tags; ?>;
    $( "#destination" ).autocomplete({
      source: tags
    });
});
</script>