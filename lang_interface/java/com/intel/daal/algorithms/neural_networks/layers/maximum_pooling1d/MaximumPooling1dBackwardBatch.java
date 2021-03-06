/* file: MaximumPooling1dBackwardBatch.java */
/*******************************************************************************
* Copyright 2014-2018 Intel Corporation.
*
* This software and the related documents are Intel copyrighted  materials,  and
* your use of  them is  governed by the  express license  under which  they were
* provided to you (License).  Unless the License provides otherwise, you may not
* use, modify, copy, publish, distribute,  disclose or transmit this software or
* the related documents without Intel's prior written permission.
*
* This software and the related documents  are provided as  is,  with no express
* or implied  warranties,  other  than those  that are  expressly stated  in the
* License.
*******************************************************************************/

/**
 * @defgroup maximum_pooling1d_backward_batch Batch
 * @ingroup maximum_pooling1d_backward
 * @{
 */
package com.intel.daal.algorithms.neural_networks.layers.maximum_pooling1d;

import com.intel.daal.algorithms.neural_networks.layers.pooling1d.Pooling1dIndex;
import com.intel.daal.algorithms.Precision;
import com.intel.daal.algorithms.ComputeMode;
import com.intel.daal.algorithms.AnalysisBatch;
import com.intel.daal.services.DaalContext;

/**
 * <a name="DAAL-CLASS-ALGORITHMS__NEURAL_NETWORKS__LAYERS__MAXIMUM_POOLING1D__MAXIMUMPOOLING1DBACKWARDBATCH"></a>
 * \brief Class that computes the results of the one-dimensional maximum pooling layer in the batch processing mode
 * <!-- \n<a href="DAAL-REF-MAXIMUMPOOLING1DBACKWARD-ALGORITHM">Backward one-dimensional maximum pooling layer description and usage models</a> -->
 *
 * \par References
 *      - @ref MaximumPooling1dLayerDataId class
 */
public class MaximumPooling1dBackwardBatch extends com.intel.daal.algorithms.neural_networks.layers.BackwardLayer {
    public  MaximumPooling1dBackwardInput input;     /*!< %Input data */
    public  MaximumPooling1dMethod        method;    /*!< Computation method for the layer */
    public  MaximumPooling1dParameter     parameter; /*!< MaximumPooling1dParameters of the layer */
    private Precision     prec;      /*!< Data type to use in intermediate computations for the layer */

    /** @private */
    static {
        System.loadLibrary("JavaAPI");
    }

    /**
     * Constructs the backward one-dimensional maximum pooling layer by copying input objects of backward one-dimensional maximum pooling layer
     * @param context    Context to manage the backward one-dimensional maximum pooling layer
     * @param other      A backward one-dimensional maximum pooling layer to be used as the source to initialize the input objects of
     *                   the backward one-dimensional maximum pooling layer
     */
    public MaximumPooling1dBackwardBatch(DaalContext context, MaximumPooling1dBackwardBatch other) {
        super(context);
        this.method = other.method;
        prec = other.prec;

        this.cObject = cClone(other.cObject, prec.getValue(), method.getValue());
        input = new MaximumPooling1dBackwardInput(context, cGetInput(cObject, prec.getValue(), method.getValue()));
        parameter = new MaximumPooling1dParameter(context, cInitParameter(cObject, prec.getValue(), method.getValue()));
    }

    /**
     * Constructs the backward one-dimensional maximum pooling layer
     * @param context    Context to manage the backward one-dimensional maximum pooling layer
     * @param cls        Data type to use in intermediate computations for the layer, Double.class or Float.class
     * @param method     The layer computation method, @ref MaximumPooling1dMethod
     * @param nDim       Number of dimensions in input data
     */
    public MaximumPooling1dBackwardBatch(DaalContext context, Class<? extends Number> cls, MaximumPooling1dMethod method, long nDim) {
        super(context);

        this.method = method;

        if (method != MaximumPooling1dMethod.defaultDense) {
            throw new IllegalArgumentException("method unsupported");
        }
        if (cls != Double.class && cls != Float.class) {
            throw new IllegalArgumentException("type unsupported");
        }

        if (cls == Double.class) {
            prec = Precision.doublePrecision;
        }
        else {
            prec = Precision.singlePrecision;
        }

        this.cObject = cInit(prec.getValue(), method.getValue(), nDim);
        input = new MaximumPooling1dBackwardInput(context, cGetInput(cObject, prec.getValue(), method.getValue()));
        parameter = new MaximumPooling1dParameter(context, cInitParameter(cObject, prec.getValue(), method.getValue()));
    }

    MaximumPooling1dBackwardBatch(DaalContext context, Class<? extends Number> cls, MaximumPooling1dMethod method, long cObject, long nDim) {
        super(context);

        this.method = method;

        if (method != MaximumPooling1dMethod.defaultDense) {
            throw new IllegalArgumentException("method unsupported");
        }
        if (cls != Double.class && cls != Float.class) {
            throw new IllegalArgumentException("type unsupported");
        }

        if (cls == Double.class) {
            prec = Precision.doublePrecision;
        }
        else {
            prec = Precision.singlePrecision;
        }

        this.cObject = cObject;
        input = new MaximumPooling1dBackwardInput(context, cGetInput(cObject, prec.getValue(), method.getValue()));
        parameter = new MaximumPooling1dParameter(context, cInitParameter(cObject, prec.getValue(), method.getValue()));
        Pooling1dIndex sd = new Pooling1dIndex(nDim - 1);
        parameter.setIndex(sd);
    }

    /**
     * Computes the result of the backward one-dimensional maximum pooling layer
     * @return  Backward one-dimensional maximum pooling layer result
     */
    @Override
    public MaximumPooling1dBackwardResult compute() {
        super.compute();
        MaximumPooling1dBackwardResult result = new MaximumPooling1dBackwardResult(getContext(), cGetResult(cObject, prec.getValue(), method.getValue()));
        return result;
    }

    /**
     * Registers user-allocated memory to store the result of the backward one-dimensional maximum pooling layer
     * @param result    Structure to store the result of the backward one-dimensional maximum pooling layer
     */
    public void setResult(MaximumPooling1dBackwardResult result) {
        cSetResult(cObject, prec.getValue(), method.getValue(), result.getCObject());
    }

    /**
     * Returns the structure that contains result of the backward layer
     * @return Structure that contains result of the backward layer
     */
    @Override
    public MaximumPooling1dBackwardResult getLayerResult() {
        return new MaximumPooling1dBackwardResult(getContext(), cGetResult(cObject, prec.getValue(), method.getValue()));
    }

    /**
     * Returns the structure that contains input object of the backward layer
     * @return Structure that contains input object of the backward layer
     */
    @Override
    public MaximumPooling1dBackwardInput getLayerInput() {
        return input;
    }

    /**
     * Returns the structure that contains parameters of the backward layer
     * @return Structure that contains parameters of the backward layer
     */
    @Override
    public MaximumPooling1dParameter getLayerParameter() {
        return parameter;
    }

    /**
     * Returns the newly allocated backward one-dimensional maximum pooling layer
     * with a copy of input objects of this backward one-dimensional maximum pooling layer
     * @param context    Context to manage the layer
     *
     * @return The newly allocated backward one-dimensional maximum pooling layer
     */
    @Override
    public MaximumPooling1dBackwardBatch clone(DaalContext context) {
        return new MaximumPooling1dBackwardBatch(context, this);
    }

    private native long cInit(int prec, int method, long nDim);
    private native long cInitParameter(long cAlgorithm, int prec, int method);
    private native long cGetInput(long cAlgorithm, int prec, int method);
    private native long cGetResult(long cAlgorithm, int prec, int method);
    private native void cSetResult(long cAlgorithm, int prec, int method, long cObject);
    private native long cClone(long algAddr, int prec, int method);
}
/** @} */
