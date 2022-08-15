interface PipelineHandler {
    fun getHandlerType(): HandlerType
    fun handle()
}
