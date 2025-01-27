package ru.uniyar.web.models

import org.http4k.lens.WebForm
import org.http4k.template.ViewModel

class EditMessageDataVM(
    val form: WebForm,
    val failures: List<String> = emptyList(),
) : ViewModel
