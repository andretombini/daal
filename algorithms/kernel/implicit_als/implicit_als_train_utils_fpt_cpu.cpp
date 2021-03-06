/* file: implicit_als_train_utils_fpt_cpu.cpp */
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

#include "implicit_als_train_utils.i"

namespace daal
{
namespace algorithms
{
namespace implicit_als
{
namespace training
{
namespace internal
{
template services::Status csr2csc<DAAL_FPTYPE, DAAL_CPU>(size_t nItems, size_t nUsers,
            const DAAL_FPTYPE *csrdata, const size_t *colIndices, const size_t *rowOffsets,
            DAAL_FPTYPE *cscdata, size_t *rowIndices, size_t *colOffsets);
}
}
}
}
}
