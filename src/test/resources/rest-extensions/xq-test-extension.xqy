xquery version "1.0-ml";
module namespace mavenDeployer =
    "http://marklogic.com/rest-api/resource/extensionName";
declare function mavenDeployer:get(
    $context as map:map,
    $params  as map:map
    let $content := 
        <args>
            {for $arg in $arg1
             return <arg1>{$arg1}</arg1>
            }
        </args>
    return document { $content } 
) as document-node()*
declare function mavenDeployer:put(
    $context as map:map,
    $params  as map:map,
    $input   as document-node()*
) as document-node()?
declare function mavenDeployer:post(
    $context as map:map,
    $params  as map:map,
    $input   as document-node()*
) as document-node()*
declare function mavenDeployer:delete(
    $context as map:map,
    $params  as map:map
) as document-node()?