package externals

@JsModule("array-sort")
//@JsNonModule - не нужно с CommonJs
external fun <T, R> arraySort(array: Array<T>, comparisonArgs: String ): Array<R>

