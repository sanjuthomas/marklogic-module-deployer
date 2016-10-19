# What is MarkLogic Module Deployer Maven Plugin?

MarkLogic Module Deployer is a maven plugin to deploy MarkLogic server side artifacts into MarkLogic module database.

# When to use MarkLogic Module Deployer Maven Plugin?

If your standard build tool is maven and the DBA is managing all but the code deployment, you may use the MarkLogic module deployer maven plugin. As developer, you can deploy MarkLogic server side artifacts into MarkLogic application server from within your IDE. As a result, better productivity.


# What are MarkLogic Server Side Artifacts?

1. REST Extension written in JavaScript or XQuery.
2. Query Options written in XML or JSON.
3. Library modules written in JavaScript or XQuery.
4. Transforms written in XQuery, XSLT or JavaScript.

# How to use the MarkLogic Module Deployer Maven Plugin?

You can use it just like any other maven plugin in your maven project. Refer to sample maven project that make use of MarkLogic Module Deployer Maven Plugin [here](https://github.com/sanjuthomas/marklogic-module-deployer-sample-project).

# Are you looking for a bootstrap tool for MarkLogic?

MarkLogic Module Deployer is written to deploy the MarkLogic server side artifacts into MarkLogic server. It's not a MarkLogic bootstrap tool. If you are looking for a comprehensive, enterprise grade and production ready MarkLogic bootstrap and management tool, please take a look at [ml-gradle](https://github.com/rjrudin/ml-gradle).

# Found a bug?

Please create an issue in github or send a note to ml@sanju.org.

# Make MarkLogic Module Deployer Maven Plugin better

If you like to contribute, please refer [here](https://github.com/sanjuthomas/marklogic-module-deployer/blob/master/CONTRIBUTING.md)