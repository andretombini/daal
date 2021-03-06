/* file: InitStep2LocalInputId.java */
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
 * @ingroup implicit_als_init_distributed
 * @{
 */
package com.intel.daal.algorithms.implicit_als.training.init;

/**
 * <a name="DAAL-CLASS-ALGORITHMS__IMPLICIT_ALS__TRAINING__INIT__INITSTEP2LOCALINPUTID"></a>
 * @brief Available identifiers of input objects for the implicit ALS initialization algorithm
 *        in the second step of the distributed processing mode
 */
public final class InitStep2LocalInputId {
    private int _value;

    /**
     * Constructs the local input object identifier using the provided value
     * @param value     Value corresponding to the local input object identifier
     */
    public InitStep2LocalInputId(int value) {
        _value = value;
    }

    /**
     * Returns the value corresponding to the local input object identifier
     * @return Value corresponding to the local input object identifier
     */
    public int getValue() {
        return _value;
    }

    private static final int InputOfStep2FromStep1 = 0;

    /**
     * Partial results of the implicit ALS initialization algorithm computed in the first step
     * and to be transferred to the second step of the distributed initialization algorithm
     */
    public static final InitStep2LocalInputId inputOfStep2FromStep1 = new InitStep2LocalInputId(InputOfStep2FromStep1);
}
/** @} */
