# databrics-community-notebooks
Notebooks and scripts created while exploring Databricks community edition
create community edition account at https://community.cloud.databricks.com/
# Assuming python is setup on your machine
pip install databricks-cli
# creating community edition cluster from command line . creates cluster with latest spark and python 3 version.
databricks clusters create --json '{ "cluster_name": "my-cluster", "spark_version": "4.3.x-scala2.11", "node_type_id": "dev-tier-node", "spark_env_vars": {"PYSPARK_PYTHON": "/databricks/python3/bin/python3"},"num_workers": 0 }'

