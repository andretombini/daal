/* file: Batch.java */
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
* @defgroup qr QR Decomposition
* @brief Contains classes for computing the results of the QR decomposition algorithm
* @ingroup analysis
* @{
*/
/**
* @defgroup qr_without_pivoting QR Decomposition without Pivoting
* @brief Contains classes for computing the results of the QR decomposition algorithm without Pivoting
* @ingroup qr
* @{
*/
/**
 * @defgroup qr_batch Batch
 * @ingroup qr_without_pivoting
 * @{
 */
/**
 * @brief Contains classes for computing the QR decomposition
 */
package com.intel.daal.algorithms.qr;

import com.intel.daal.algorithms.AnalysisBatch;
import com.intel.daal.algorithms.ComputeMode;
import com.intel.daal.algorithms.Precision;
import com.intel.daal.services.DaalContext;

/**
 * <a name="DAAL-CLASS-ALGORITHMS__QR__BATCH"></a>
 * @brief Computes the results of the QR decomposition algorithm in the batch processing mode
 * <!-- \n<a href="DAAL-REF-QR-ALGORITHM">QR algorithm description and usage models</a> -->
 *
 * @par References
 *      - InputId class. Identifiers of input objects for the QR decomposition algorithm
 *      - ResultId class. Identifiers of the results of the algorithm
 */
public class Batch extends AnalysisBatch {
    public Input      input; /*!< %Input data */
    public Method     method; /*!< Computation method for the algorithm */
    private Precision                 prec; /*!< Precision of intermediate computations */

    /** @private */
    static {
        System.loadLibrary("JavaAPI");
    }

    /**
     * Constructs the QR decomposition algorithm by copying input objects and parameters
     * of another QR decomposition algorithm
     * @param context   Context to manage the QR decomposition algorithm
     * @param other     An algorithm to be used as the source to initialize the input objects
     *                  and parameters of the algorithm
     */
    public Batch(DaalContext context, Batch other) {
        super(context);
        this.method = other.method;
        prec = other.prec;

        this.cObject = cClone(other.cObject, prec.getValue(), method.getValue());
        input = new Input(getContext(), cGetInput(cObject, prec.getValue(), method.getValue()));
    }

    /**
     * Constructs the QR decomposition algorithm
     * @param context   Context to manage the QR decomposition algorithm
     * @param cls       Data type to use in intermediate computations for the QR decomposition algorithm,
     *                  Double.class or Float.class
     * @param method    Computation method, @ref Method
     */
    public Batch(DaalContext context, Class<? extends Number> cls, Method method) {
        super(context);

        this.method = method;

        if (method != Method.defaultDense) {
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

        this.cObject = cInit(prec.getValue(), method.getValue());
        input = new Input(getContext(), cGetInput(cObject, prec.getValue(), method.getValue()));
    }

    /**
     * Runs the QR decomposition algorithm
     * @return  Results of the QR decomposition algorithm
     */
    @Override
    public Result compute() {
        super.compute();
        return new Result(getContext(), cGetResult(cObject, prec.getValue(), method.getValue()));
    }

    /**
     * Registers user-allocated memory for storing the results of the QR decomposition algorithm
     * @param result Structure for storing the results of the QR decomposition algorithm
     */
    public void setResult(Result result) {
        cSetResult(cObject, prec.getValue(), method.getValue(), result.getCObject());
    }

    /**
     * Returns the newly allocated QR decomposition algorithm
     * with a copy of input objects and parameters of this QR decomposition algorithm
     * @param context   Context to manage the QR decomposition algorithm
     *
     * @return The newly allocated algorithm
     */
    @Override
    public Batch clone(DaalContext context) {
        return new Batch(context, this);
    }

    private native long cInit(int prec, int method);

    private native long cGetInput(long algAddr, int prec, int method);

    private native long cGetResult(long algAddr, int prec, int method);

    private native void cSetResult(long cAlgorithm, int prec, int method, long cResult);

    private native long cClone(long algAddr, int prec, int method);
}
/** @} */
/** @} */
/** @} */
