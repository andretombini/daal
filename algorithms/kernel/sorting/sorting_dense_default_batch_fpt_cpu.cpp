/* file: sorting_dense_default_batch_fpt_cpu.cpp */
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

/*
//++
//  Implementation of SortingKernel for hsw.
//--
*/

#include "sorting_batch_container.h"
#include "sorting_kernel.h"
#include "sorting_impl.i"

namespace daal
{
namespace algorithms
{
namespace sorting
{
namespace interface1
{

template class BatchContainer<DAAL_FPTYPE, defaultDense, DAAL_CPU>;

}
namespace internal
{

template class SortingKernel<defaultDense, DAAL_FPTYPE, DAAL_CPU>;

} // namespace daal::algorithms::sorting::internal

} // namespace daal::algorithms::sorting

} // namespace daal::algorithms

} // namespace daal
