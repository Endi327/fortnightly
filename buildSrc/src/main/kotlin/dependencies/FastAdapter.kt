package dependencies

interface FastAdapterDependencies {
    val fastAdapter: String
    val fastAdapterBinding: String
    val fastAdapterDiff: String
    val fastAdapterExpandable: String
    val fastAdapterSwipe: String
    val fastAdapterDrag: String
    val fastAdapterScroll: String
    val fastAdapterUtils: String
    val fastAdapterPaged: String
}

internal object FastAdapter : FastAdapterDependencies {
    private const val version = "5.4.1"

    override val fastAdapter = "com.mikepenz:fastadapter:$version"
    override val fastAdapterBinding = "com.mikepenz:fastadapter-extensions-binding:$version"
    override val fastAdapterDiff = "com.mikepenz:fastadapter-extensions-diff:$version"
    override val fastAdapterExpandable = "com.mikepenz:fastadapter-extensions-expandable:$version"
    override val fastAdapterSwipe = "com.mikepenz:fastadapter-extensions-swipe:$version"
    override val fastAdapterDrag = "com.mikepenz:fastadapter-extensions-drag:$version"
    override val fastAdapterScroll = "com.mikepenz:fastadapter-extensions-scroll:$version"
    override val fastAdapterUtils = "com.mikepenz:fastadapter-extensions-utils:$version"
    override val fastAdapterPaged = "com.mikepenz:fastadapter-extensions-paged:$version"
}

