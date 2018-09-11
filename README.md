# databrics-community-notebooks
Notebooks and scripts created while exploring Databricks community edition

create community edition account at 
> https://community.cloud.databricks.com/

Assuming python is setup on your machine:

>pip install databricks-cli

Run command:

> databricks configure

provide your credentials

Creating community edition cluster from command line . creates cluster with latest spark and python 3 version.

> databricks clusters create --json '{ "cluster_name": "my-cluster", "spark_version": "4.3.x-scala2.11", "node_type_id": "dev-tier-node", "spark_env_vars": {"PYSPARK_PYTHON": "/databricks/python3/bin/python3"},"num_workers": 0 }'

Thsi will return a json response with a cluster-id(your-cluster-id): Use this cluster-id in the below commands.
To get some of the libraries used in the tutorials pre-loaded, run the following commands

> databricks libraries install --cluster-id your-cluster-id --pypi-package bokeh

> databricks libraries install --cluster-id your-cluster-id --pypi-package nsepy

> databricks libraries install --cluster-id your-cluster-id --pypi-package plotly

> databricks libraries install --cluster-id your-cluster-id --pypi-package quandl

Workspace > import> files
import any of the notebooks for exploration

