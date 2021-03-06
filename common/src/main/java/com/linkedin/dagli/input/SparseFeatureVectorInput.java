package com.linkedin.dagli.input;

import com.linkedin.dagli.math.vector.DenseVector;
import com.linkedin.dagli.math.vector.Vector;
import com.linkedin.dagli.producer.Producer;
import java.util.function.Function;


/**
 * Input configurator for sparse vector producer inputs.
 *
 * @param <T> the type of thing whose input is to be configured
 */
public class SparseFeatureVectorInput<T>
    extends AbstractFeatureVectorInput<Void, T, SparseFeatureVectorInput<T>> {
  private final Function<Producer<? extends Vector>, T> _withInputFunction;

  /**
   * Creates a new instance that will use the specified with-vector-input method to create its result.
   * @param withInputFunction a method accepting a vector input producer and returning the configured object
   */
  public SparseFeatureVectorInput(Function<Producer<? extends Vector>, T> withInputFunction) {
    _withInputFunction = withInputFunction;
  }

  @Override
  protected T fromVector(Producer<? extends Vector> vector) {
    return _withInputFunction.apply(vector);
  }

  @Override
  protected T fromDenseVector(Producer<? extends DenseVector> vector) {
    return fromVector(vector);
  }

  /**
   * Gets an "aggregated" configurator that combines multiple input values into a single input vector.
   *
   * After all input values have been specified, call {@link WithFeatureVectorInput.Aggregated#done()} to obtain the configured transformer.
   *
   * @return an "aggregated" configurator that combines multiple input values into a single input vector
   */
  public Aggregated combining() {
    return new Aggregated();
  }

  /**
   * Input configurator that aggregates multiple inputs (until {@link #done()} is called).
   */
  public class Aggregated extends AbstractFeatureVectorInput<Producer<? extends Vector>, Aggregated, Aggregated> {
    @Override
    protected Aggregated fromVector(Producer<? extends Vector> vector) {
      return withAddedInput(vector);
    }

    @Override
    protected Aggregated fromDenseVector(Producer<? extends DenseVector> vector) {
      return withAddedInput(vector);
    }

    /**
     * Called when all inputs have been added.
     *
     * @return the resulting object that will accept the configured aggregated input
     */
    public T done() {
      return SparseFeatureVectorInput.this.fromVectors(getInputs());
    }
  }
}
