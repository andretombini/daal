/* file: InitBatch.java */
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
 * @defgroup em_gmm Expectation-Maximization
 * @brief Contains classes for the EM for GMM algorithm
 * @ingroup analysis
 * @defgroup em_gmm_init Initialization
 * @brief Contains classes for the EM for GMM initialization algorithm
 * @ingroup em_gmm
 * @{
 */
/**
 * @defgroup em_gmm_init_batch Batch
 * @ingroup em_gmm_init
 * @{
 */
package com.intel.daal.algorithms.em_gmm.init;

import com.intel.daal.algorithms.AnalysisBatch;
import com.intel.daal.algorithms.ComputeMode;
import com.intel.daal.algorithms.Precision;
import com.intel.daal.services.DaalContext;

/**
 * <a name="DAAL-CLASS-ALGORITHMS__EM_GMM__INIT__INITBATCH"></a>
 * \brief Computes initial values for the EM for GMM algorithm in the batch processing mode.
 * <!-- \n<a href="DAAL-REF-EM_GMM-ALGORITHM">EM for GMM algorithm description and usage models.</a> -->
 *
 * \par References
 *      - @ref InitInputId class
 *      - @ref InitResultId class
 *
 */
public class InitBatch extends AnalysisBatch {
    public InitParameter parameter;   /*!< Parameters for the initialization algorithm */
    public InitInput     input; /*!< %Input data */
    public InitMethod    method;  /*!< Initialization method for the algorithm */
    private InitResult   result; /*!< %Result of the initialization algorithm */
    private Precision precision; /*!< Precision of intermediate computations */

    /** @private */
    static {
        System.loadLibrary("JavaAPI");
    }

    /**
     * Constructs the algorithm for computing initial values for the EM for GMM algorithm by copying input objects
     * and parameters of another algorithm for computing initial values for the EM for GMM algorithm
     * @param context      Context to manage the EM for GMM algorithm
     * @param other        An algorithm to be used as the source to initialize the input objects
     *                     and parameters of the algorithm
     */
    public InitBatch(DaalContext context, InitBatch other) {
        super(context);
        this.method = other.method;
        precision = other.precision;

        this.cObject = cClone(other.cObject, precision.getValue(), this.method.getValue());

        input = new InitInput(getContext(), cObject, precision, method, ComputeMode.batch);
        parameter = new InitParameter(getContext(), cInitParameter(this.cObject, precision.getValue(), method.getValue()));
    }

    /**
     * <a name="DAAL-METHOD-ALGORITHM__EM_GMM__INIT__INITBATCH__INITBATCH"></a>
     * Constructs the algorithm for computing initial values for the EM for GMM algorithm
     *
     * @param context      Context to manage the EM for GMM algorithm
     * @param cls          Data type to use in intermediate computations for the EM for GMM algorithm, Double.class or Float.class
     * @param method       Initial method of the EM for GMM algorithm
     * @param nComponents  Number of components in the Gaussian mixture model
     */
    public InitBatch(DaalContext context, Class<? extends Number> cls, InitMethod method, long nComponents) {
        super(context);
        this.method = method;
        if (cls != Double.class && cls != Float.class) {
            throw new IllegalArgumentException("type unsupported");
        }

        if (this.method != InitMethod.defaultDense) {
            throw new IllegalArgumentException("method unsupported");
        }

        if (cls == Double.class) {
            precision = Precision.doublePrecision;
        } else {
            precision = Precision.singlePrecision;
        }

        this.cObject = cInit(precision.getValue(), this.method.getValue(), nComponents);

        input = new InitInput(getContext(), cObject, precision, method, ComputeMode.batch);
        parameter = new InitParameter(getContext(), this.cObject, precision.getValue(), method.getValue(),
                ComputeMode.batch.getValue(), nComponents, 20, 10, 1.0e-04);
    }

    /**
    * Computes initial values for the EM for GMM algorithm
    * @return Results of the EM for GMM algorithm
    */
    @Override
    public InitResult compute() {
        super.compute();
        InitResult result = new InitResult(getContext(), cGetResult(cObject, precision.getValue(), method.getValue()));
        return result;
    }

    /**
    * Sets the memory for storing initialized values for the EM for GMM algorithm
    * @param result Structure for storing initialized values for the EM for GMM algorithm
    */
    public void setResult(InitResult result) {
        cSetResult(cObject, precision.getValue(), method.getValue(), result.getCObject());
    }

    /**
     * Returns the newly allocated algorithm for computing initial values for the EM for GMM algorithm
     * with a copy of input objects of this algorithm
     * @param context      Context to manage the EM for GMM algorithm
     *
     * @return The newly allocated algorithm
     */
    @Override
    public InitBatch clone(DaalContext context) {
        return new InitBatch(context, this);
    }

    private native long cInit(int precision, int method, long nComponents);

    private native long cGetResult(long cObject, int prec, int method);

    private native void cSetResult(long cObject, int prec, int method, long cResult);

    private native long cInitParameter(long algAddr, int prec, int method);

    private native long cClone(long algAddr, int prec, int method);
}
/** @} */
/** @} */
/** @} */
