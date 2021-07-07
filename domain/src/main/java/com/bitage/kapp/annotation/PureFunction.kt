package com.bitage.kapp.annotation

/**
 * If you annotate method with this annotation you provide:
 * # require at least one argument
 * # doesn't change any arguments
 * # return a result
 */
@Target(AnnotationTarget.FUNCTION)
annotation class PureFunction