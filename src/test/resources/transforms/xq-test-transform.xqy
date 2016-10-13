xquery version "1.0-ml";
module namespace example = "http://marklogic.com/rest-api/transform/xq-test-transform";

declare function example:transform(
  $context as map:map,
  $params as map:map,
  $content as document-node()
) as document-node()
{

 let $newContent := 
        <args>
            test
        </args>
    return document { $newContent } 
 
};