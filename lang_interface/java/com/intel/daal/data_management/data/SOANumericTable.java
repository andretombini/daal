/* file: SOANumericTable.java */
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
 * @ingroup numeric_tables
 * @{
 */
package com.intel.daal.data_management.data;

import java.nio.Buffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Vector;

import com.intel.daal.services.DaalContext;

/**
 * <a name="DAAL-CLASS-DATA_MANAGEMENT__DATA__SOANUMERICTABLE"></a>
 * @brief Class that provides methods to access data that is stored as a Structure
 *        Of Arrays(SOA), where each contiguous array represents values
 *        corresponding to a specific feature
 */
public class SOANumericTable extends NumericTable {
    /** @private */
    static {
        System.loadLibrary("JavaAPI");
    }

    public SOANumericTable(DaalContext context, SOANumericTableImpl impl) {
        super(context);
        tableImpl = impl;
    }

    /**
     * Constructs a Structure Of Arrays(SOA) numeric table
     *
     * @param context   Context to manage created numeric table
     * @param nFeatures Number of features in numeric table
     * @param nVectors  Number of feature vectors in numeric table
     */
    public SOANumericTable(DaalContext context, long nFeatures, long nVectors) {
        super(context);
        tableImpl = new SOANumericTableImpl(context, nFeatures, nVectors);
    }

    /**
     * Sets array of doubles of the feature to the table
     *
     * @param arr Array of values of the feature
     * @param idx Index of the feature
     */
    public void setArray(double[] arr, long idx) {
        ((SOANumericTableImpl)tableImpl).setArray(arr, idx);
    }

    /**
     * Sets array of floats of the feature to the table
     *
     * @param arr Array of values of the feature
     * @param idx Index of the feature
     */
    public void setArray(float[] arr, long idx) {
        ((SOANumericTableImpl)tableImpl).setArray(arr, idx);
    }

    /**
     * Sets array of longs of the feature to the table
     *
     * @param arr Array of values of the feature
     * @param idx Index of the feature
     */
    public void setArray(long[] arr, long idx) {
        ((SOANumericTableImpl)tableImpl).setArray(arr, idx);
    }

    /**
     * Sets array of integers of the feature to the table
     *
     * @param arr Array of values of the feature
     * @param idx Index of the feature
     */
    public void setArray(int[] arr, long idx) {
        ((SOANumericTableImpl)tableImpl).setArray(arr, idx);
    }
}
/** @} */
