package ru.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.FORBIDDEN
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.lens.RequestContextLens
import org.http4k.lens.WebForm
import ru.uniyar.authorization.Permissions
import ru.uniyar.web.models.AuthPageVM
import ru.uniyar.web.templates.ContextAwareViewRender

class ShowAuthPageHandler(
    val webForm: WebForm,
    val lens: ContextAwareViewRender,
    val permissionLens: RequestContextLens<Permissions>,
) : HttpHandler {
    override fun invoke(request: Request): Response {
        val role = permissionLens(request)
        if (role.name != "ANONYMOUS") return Response(FORBIDDEN)
        val model = AuthPageVM(webForm)
        return Response(OK).with(lens(request) of model)
    }
}