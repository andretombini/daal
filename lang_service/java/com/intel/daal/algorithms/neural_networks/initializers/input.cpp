/* file: input.cpp */
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

#include <jni.h>
#include "neural_networks/initializers/JInput.h"
#include "neural_networks/initializers/JInputId.h"

#include "daal.h"

#include "common_helpers.h"

#define dataId com_intel_daal_algorithms_neural_networks_initializers_InputId_dataId

USING_COMMON_NAMESPACES();
using namespace daal::algorithms::neural_networks;

/*
 * Class:     com_intel_daal_algorithms_neural_networks_initializers_Input
 * Method:    cSetInput
 * Signature: (JIJ)V
 */
JNIEXPORT void JNICALL Java_com_intel_daal_algorithms_neural_1networks_initializers_Input_cSetInput
  (JNIEnv *env, jobject thisObj, jlong inputAddr, jint id, jlong tensorAddr)
{
    if (id == dataId)
    {
        jniInput<initializers::Input>::set<initializers::InputId, Tensor>(inputAddr, id, tensorAddr);
    }
}

/*
 * Class:     com_intel_daal_algorithms_neural_networks_initializers_Input
 * Method:    cGetInput
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_com_intel_daal_algorithms_neural_1networks_initializers_Input_cGetInput
  (JNIEnv *env, jobject thisObj, jlong inputAddr, jint id)
{
    return jniInput<initializers::Input>::get<initializers::InputId, Tensor>(inputAddr, id);
}
