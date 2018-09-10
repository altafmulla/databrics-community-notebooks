// Databricks notebook source
 val nsecombined = spark.table("nsecombinedall")

// COMMAND ----------

display(nsecombined.groupBy("ticker").count())

// COMMAND ----------

(nsecombined.groupBy("ticker").ohlc())

// COMMAND ----------

display(nsecombined.filter(nsecombined("ticker")==="HINDALCO"))

// COMMAND ----------

display(spark.sql("SELECT TO_DATE(Date) AS date, open AS open, high AS high, low AS low, adj_close AS close FROM nsecombinedall where ticker='TCS' limit 5"))

// COMMAND ----------

// MAGIC %python
// MAGIC df=spark.sql("SELECT TO_DATE(Date) AS date, open AS open, high AS high, low AS low, close AS close FROM nsecombinedall where ticker='TCS'")

// COMMAND ----------

// MAGIC %python
// MAGIC df.show()

// COMMAND ----------

// MAGIC %sh
// MAGIC /databricks/python/bin/pip install --upgrade pip

// COMMAND ----------

// MAGIC %python
// MAGIC df=df.toPandas()

// COMMAND ----------

// MAGIC %python
// MAGIC df.head()

// COMMAND ----------

// MAGIC %python
// MAGIC df.plot()
// MAGIC display()

// COMMAND ----------

// MAGIC 
// MAGIC %python
// MAGIC import plotly.plotly as py
// MAGIC import plotly.graph_objs as go
// MAGIC from datetime import datetime
// MAGIC trace = go.Ohlc(x=df.date,
// MAGIC                 open=df.open,
// MAGIC                 high=df.high,
// MAGIC                 low=df.low,
// MAGIC                 close=df.close)
// MAGIC data = [trace]
// MAGIC 
// MAGIC import plotly.offline as offline
// MAGIC import plotly.graph_objs as go
// MAGIC 
// MAGIC #offline.init_notebook_mode()
// MAGIC offline.init_notebook_mode(connected=True)
// MAGIC p=offline.plot(data, output_type='div')
// MAGIC displayHTML(p)

// COMMAND ----------

// MAGIC %python
// MAGIC import plotly.plotly as py
// MAGIC import plotly.graph_objs as go
// MAGIC from datetime import datetime
// MAGIC trace = go.Candlestick(x=df.date,
// MAGIC                 open=df.open,
// MAGIC                 high=df.high,
// MAGIC                 low=df.low,
// MAGIC                 close=df.close)
// MAGIC data = [trace]
// MAGIC 
// MAGIC import plotly.offline as offline
// MAGIC import plotly.graph_objs as go
// MAGIC 
// MAGIC offline.init_notebook_mode()
// MAGIC 
// MAGIC layout = go.Layout(
// MAGIC     xaxis = dict(
// MAGIC         rangeslider = dict(
// MAGIC             visible = False
// MAGIC         )
// MAGIC     )
// MAGIC )
// MAGIC 
// MAGIC p=offline.plot(data, output_type='div')
// MAGIC displayHTML(p)

// COMMAND ----------

// MAGIC %sh
// MAGIC /databricks/python/bin/pip install --upgrade bokeh

// COMMAND ----------

// MAGIC %python
// MAGIC import bokeh
// MAGIC bokeh.sampledata.download()

// COMMAND ----------

// MAGIC %python
// MAGIC from math import pi
// MAGIC 
// MAGIC import pandas as pd
// MAGIC 
// MAGIC from bokeh.plotting import figure, show, output_file
// MAGIC from bokeh.sampledata.stocks import MSFT
// MAGIC from bokeh.resources import CDN
// MAGIC from bokeh.embed import components, file_html
// MAGIC 
// MAGIC df = pd.DataFrame(MSFT)[:50]
// MAGIC df["date"] = pd.to_datetime(df["date"])
// MAGIC 
// MAGIC inc = df.close > df.open
// MAGIC dec = df.open > df.close
// MAGIC w = 12*60*60*1000 # half day in ms
// MAGIC 
// MAGIC TOOLS = "pan,wheel_zoom,box_zoom,reset,save"
// MAGIC 
// MAGIC p = figure(x_axis_type="datetime", tools=TOOLS, plot_width=1000, title = "MSFT Candlestick")
// MAGIC p.xaxis.major_label_orientation = pi/4
// MAGIC p.grid.grid_line_alpha=0.3
// MAGIC 
// MAGIC p.segment(df.date, df.high, df.date, df.low, color="black")
// MAGIC p.vbar(df.date[inc], w, df.open[inc], df.close[inc], fill_color="#D5E1DD", line_color="black")
// MAGIC p.vbar(df.date[dec], w, df.open[dec], df.close[dec], fill_color="#F2583E", line_color="black")
// MAGIC 
// MAGIC #output_file("candlestick.html", title="candlestick.py example")
// MAGIC 
// MAGIC #show(p)  # open a browser
// MAGIC # create an html document that embeds the Bokeh plot
// MAGIC html = file_html(p, CDN, "my plot1")
// MAGIC 
// MAGIC # display this html
// MAGIC displayHTML(html)

// COMMAND ----------

// MAGIC %python
// MAGIC df.head()

// COMMAND ----------

// MAGIC %python
// MAGIC from math import pi
// MAGIC 
// MAGIC import pandas as pd
// MAGIC 
// MAGIC from bokeh.plotting import figure, show, output_file
// MAGIC from bokeh.sampledata.stocks import MSFT
// MAGIC from bokeh.resources import CDN
// MAGIC from bokeh.embed import components, file_html
// MAGIC 
// MAGIC #df = pd.DataFrame(MSFT)[:50]
// MAGIC df["date"] = pd.to_datetime(df["date"])
// MAGIC 
// MAGIC inc = df.close > df.open
// MAGIC dec = df.open > df.close
// MAGIC w = 12*60*60*1000 # half day in ms
// MAGIC 
// MAGIC TOOLS = "pan,wheel_zoom,box_zoom,reset,save"
// MAGIC 
// MAGIC p = figure(x_axis_type="datetime", tools=TOOLS, plot_width=1000, title = "TCS Candlestick")
// MAGIC p.xaxis.major_label_orientation = pi/4
// MAGIC p.grid.grid_line_alpha=0.3
// MAGIC 
// MAGIC p.segment(df.date, df.high, df.date, df.low, color="black")
// MAGIC p.vbar(df.date[inc], w, df.open[inc], df.close[inc], fill_color="#D5E1DD", line_color="black")
// MAGIC p.vbar(df.date[dec], w, df.open[dec], df.close[dec], fill_color="#F2583E", line_color="black")
// MAGIC 
// MAGIC #output_file("candlestick.html", title="candlestick.py example")
// MAGIC 
// MAGIC #show(p)  # open a browser
// MAGIC # create an html document that embeds the Bokeh plot
// MAGIC html = file_html(p, CDN, "my plot1")
// MAGIC 
// MAGIC # display this html
// MAGIC displayHTML(html)
