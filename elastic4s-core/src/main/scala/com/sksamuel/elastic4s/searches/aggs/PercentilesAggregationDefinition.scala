package com.sksamuel.elastic4s.searches.aggs

import com.sksamuel.elastic4s.script.ScriptDefinition
import com.sksamuel.elastic4s.searches.aggs.pipeline.PipelineAggregationDefinition
import com.sksamuel.exts.OptionImplicits._

case class PercentilesAggregationDefinition(name: String,
                                            field: Option[String] = None,
                                            missing: Option[AnyRef] = None,
                                            format: Option[String] = None,
                                            script: Option[ScriptDefinition] = None,
                                            percents: Seq[Double] = Nil,
                                            compression: Option[Double] = None,
                                            pipelines: Seq[PipelineAggregationDefinition] = Nil,
                                            subaggs: Seq[AggregationDefinition] = Nil,
                                            metadata: Map[String, AnyRef] = Map.empty)
  extends AggregationDefinition {

  type T = PercentilesAggregationDefinition

  def percents(first: Double, rest: Double*): T = percents(first +: rest)
  def percents(percents: Iterable[Double]): T = copy(percents = percents.toSeq)

  def compression(compression: Double): T = copy(compression = compression.some)

  def format(format: String): T = copy(format = format.some)
  def field(field: String): T = copy(field = field.some)
  def missing(missing: AnyRef): T = copy(missing = missing.some)
  def script(script: ScriptDefinition): T = copy(script = script.some)

  override def pipelines(pipelines: Iterable[PipelineAggregationDefinition]): T = copy(pipelines = pipelines.toSeq)
  override def subAggregations(aggs: Iterable[AggregationDefinition]): T = copy(subaggs = aggs.toSeq)
  override def metadata(map: Map[String, AnyRef]): PercentilesAggregationDefinition = copy(metadata = metadata)

}
