package com.gradle.shopifyapp.model

data class OrderModel(
    val app_id: Long?= null,
    val billing_address: BillingAddress?= null,
    val browser_ip: String?= null,
    val buyer_accepts_marketing: Boolean?= null,
    val cancel_reason: String?= null,
    val cancelled_at: Any?= null,
    val cart_token: String?= null,
    val checkout_token: String?= null,
    val client_details: ClientDetails?= null,
    val closed_at: String?= null,
    val created_at: String?= null,
    val currency: String?= null,
    val current_subtotal_price: String?= null,
    val current_subtotal_price_set: CurrentSubtotalPriceSet?= null,
    val current_total_discounts: String?= null,
    val current_total_discounts_set: CurrentTotalDiscountsSet?= null,
    val current_total_duties_set: CurrentTotalDutiesSet?= null,
    val current_total_price: String?= null,
    val current_total_price_set: CurrentTotalPriceSet?= null,
    val current_total_tax: String?= null,
    val current_total_tax_set: CurrentTotalTaxSet?= null,
    val customer: Customer?= null,
    val customer_locale: String?= null,
    val discount_applications: List<DiscountApplication>?= null,
    var discount_codes: List<DiscountCode?>?= null,
    var email: String?= null,
    val estimated_taxes: Boolean?= null,
    val financial_status: String?= null,
    val fulfillment_status: String?= null,
    val fulfillments: List<Fulfillment?>?= null,
    val gateway: String?= null,
    val id: Long?= null,
    val landing_site: String?= null,
    var line_items: List<LineItem?>?= null,
    val location_id: Int?= null,
    val name: String?= null,
    val note: String?= null,
    val note_attributes: List<NoteAttribute?>?= null,
    val number: Int?= null,
    val order_number: Int?= null,
    val order_status_url: String?= null,
    val original_total_duties_set: OriginalTotalDutiesSet?= null,
    val payment_details: PaymentDetails?= null,
    val payment_gateway_names: List<String?>?= null,
    val payment_terms: PaymentTerms?= null,
    val phone: String?= null,
    val presentment_currency: String?= null,
    val processed_at: String?= null,
    val processing_method: String?= null,
    val referring_site: String?= null,
    val refunds: List<Refund?>?= null,
    var shipping_address: ShippingAddress?= null,
    val shipping_lines: List<ShippingLine?>?= null,
    val source_identifier: String?= null,
    val source_name: String?= null,
    val source_url: String?= null,
    val subtotal_price: Int?= null,
    val subtotal_price_set: SubtotalPriceSet?= null,
    val tags: String?= null,
    val tax_lines: List<TaxLine?>?= null,
    val taxes_included: Boolean?= null,
    val test: Boolean?= null,
    val token: String?= null,
    val total_discounts: String?= null,
    val total_discounts_set: TotalDiscountsSet?= null,
    val total_line_items_price: String?= null,
    val total_line_items_price_set: TotalLineItemsPriceSet?= null,
    val total_outstanding: String?= null,
    val total_price: String?= null,
    val total_price_set: TotalPriceSet?= null,
    val total_shipping_price_set: TotalShippingPriceSet?= null,
    val total_tax: String?= null,
    val total_tax_set: TotalTaxSet?= null,
    val total_tip_received: String?= null,
    val total_weight: Int?= null,
    val updated_at: String?= null,
    val user_id: Int?= null
) {
    data class BillingAddress(
        val address1: String?,
        val address2: String?,
        val city: String?,
        val company: Any?,
        val country: String?,
        val country_code: String?,
        val first_name: String?,
        val last_name: String?,
        val latitude: String?,
        val longitude: String?,
        val name: String?,
        val phone: String?,
        val province: String?,
        val province_code: String?,
        val zip: String?
    )

    data class ClientDetails(
        val accept_language: String?,
        val browser_height: Int?,
        val browser_ip: String?,
        val browser_width: Int?,
        val session_hash: String?,
        val user_agent: String?
    )

    data class CurrentSubtotalPriceSet(
        val current_subtotal_price_set: CurrentSubtotalPriceSet?
    ) {
        data class CurrentSubtotalPriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }
    }

    data class CurrentTotalDiscountsSet(
        val current_total_discounts_set: CurrentTotalDiscountsSet?
    ) {
        data class CurrentTotalDiscountsSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }
    }

    data class CurrentTotalDutiesSet(
        val current_total_duties_set: CurrentTotalDutiesSet?
    ) {
        data class CurrentTotalDutiesSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }
    }

    data class CurrentTotalPriceSet(
        val current_total_price_set: CurrentTotalPriceSet?
    ) {
        data class CurrentTotalPriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }
    }

    data class CurrentTotalTaxSet(
        val current_total_tax_set: CurrentTotalTaxSet?
    ) {
        data class CurrentTotalTaxSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }
    }

    data class Customer(
        val accepts_marketing: Boolean?,
        val addresses: Addresses?,
        val admin_graphql_api_id: String?,
        val created_at: String?,
        val currency: String?,
        val default_address: DefaultAddress?,
        val email: String?,
        val first_name: String?,
        val id: Long?,
        val last_name: String?,
        val multipass_identifier: Any?,
        val note: Any?,
        val phone: String?,
        val state: String?,
        val tags: String?,
        val tax_exempt: Boolean?,
        val tax_exemptions: List<TaxExemptions>?= null,
        val updated_at: String?,
        val verified_email: Boolean?
    ) {
        class Addresses

        class DefaultAddress

        class TaxExemptions
    }


        data class DiscountApplication(
            val allocation_method: String?,
            val code: String?,
            val description: String?,
            val target_selection: String?,
            val target_type: String?,
            val title: String?,
            val type: String?,
            val value: String?,
            val value_type: String?
        )


    data class DiscountCode(
        val amount: String?,
        var code: String?,
        val type: String?
    )

    data class Fulfillment(
        val created_at: String?,
        val id: Int?,
        val order_id: Int?,
        val status: String?,
        val tracking_company: String?,
        val tracking_number: String?,
        val updated_at: String?
    )

    data class LineItem(
        val discount_allocations: List<DiscountAllocation?>?=null,
        val duties: List<Duty?>?=null,
        val fulfillable_quantity: Int?=null,
        val fulfillment_service: String?=null,
        val fulfillment_status: String?=null,
        val gift_card: Boolean?=null,
        val grams: Int?=null,
        val id: Long?=null,
        val name: String?=null,
        val origin_location: OriginLocation?=null,
        val price: String?=null,
        val price_set: PriceSet?=null,
        val product_id: Long?=null,
        val properties: List<Property?>?=null,
        var quantity: Int?=null,
        val requires_shipping: Boolean?=null,
        val sku: String?=null,
        val tax_lines: List<TaxLine?>?=null,
        val taxable: Boolean?=null,
        val title: String?=null,
        val total_discount: String?=null,
        val total_discount_set: TotalDiscountSet?=null,
        var variant_id: Long?=null,
        val variant_title: String?=null,
        val vendor: String?=null
    ) {
        data class DiscountAllocation(
            val amount: String?,
            val amount_set: AmountSet?,
            val discount_application_index: Int?
        ) {
            data class AmountSet(
                val presentment_money: PresentmentMoney?,
                val shop_money: ShopMoney?
            ) {
                data class PresentmentMoney(
                    val amount: String?,
                    val currency_code: String?
                )

                data class ShopMoney(
                    val amount: String?,
                    val currency_code: String?
                )
            }
        }

        data class Duty(
            val admin_graphql_api_id: String?,
            val country_code_of_origin: String?,
            val harmonized_system_code: String?,
            val id: String?,
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?,
            val tax_lines: List<TaxLine?>?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class TaxLine(
                val channel_liable: Boolean?,
                val price: String?,
                val price_set: PriceSet?,
                val rate: Double?,
                val title: String?
            ) {
                data class PriceSet(
                    val presentment_money: PresentmentMoney?,
                    val shop_money: ShopMoney?
                ) {
                    data class PresentmentMoney(
                        val amount: String?,
                        val currency_code: String?
                    )

                    data class ShopMoney(
                        val amount: String?,
                        val currency_code: String?
                    )
                }
            }
        }

        data class OriginLocation(
            val address1: String?,
            val address2: String?,
            val city: String?,
            val country_code: String?,
            val id: Long?,
            val name: String?,
            val province_code: String?,
            val zip: String?
        )

        data class PriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }

        data class Property(
            val name: String?,
            val value: String?
        )

        data class TaxLine(
            val channel_liable: Boolean?,
            val price: String?,
            val price_set: PriceSet?,
            val rate: Double?,
            val title: String?
        ) {
            data class PriceSet(
                val presentment_money: PresentmentMoney?,
                val shop_money: ShopMoney?
            ) {
                data class PresentmentMoney(
                    val amount: String?,
                    val currency_code: String?
                )

                data class ShopMoney(
                    val amount: String?,
                    val currency_code: String?
                )
            }
        }

        data class TotalDiscountSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }
    }

    data class NoteAttribute(
        val name: String?,
        val value: String?
    )



    data class OriginalTotalDutiesSet(
        val original_total_duties_set: OriginalTotalDutiesSet?
    ) {
        data class OriginalTotalDutiesSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }
    }

    data class PaymentDetails(
        val avs_result_code: String?,
        val credit_card_bin: String?,
        val credit_card_company: String?,
        val credit_card_number: String?,
        val cvv_result_code: String?
    )

    data class PaymentTerms(
        val amount: Int?,
        val currency: String?,
        val due_in_days: Int?,
        val payment_schedules: List<PaymentSchedule?>?,
        val payment_terms_name: String?,
        val payment_terms_type: String?
    ) {
        data class PaymentSchedule(
            val amount: Int?,
            val completed_at: String?,
            val currency: String?,
            val due_at: String?,
            val expected_payment_method: String?,
            val issued_at: String?
        )
    }

    data class Refund(
        val created_at: String?,
        val id: Long?,
        val note: Any?,
        val order_adjustments: List<Any?>?,
        val order_id: Long?,
        val processed_at: String?,
        val refund_line_items: List<Any?>?,
        val transactions: List<Any?>?,
        val user_id: Any?
    )

    data class ShippingAddress(
        var address1: String?,
        val address2: String?,
        val city: String?,
        val company: Any?,
        val country: String?,
        val country_code: String?,
        val first_name: String?,
        val last_name: String?,
        val latitude: String?,
        val longitude: String?,
        val name: String?,
        val phone: String?,
        val province: String?,
        val province_code: String?,
        val zip: String?
    )

    data class ShippingLine(
        val carrier_identifier: String?,
        val code: String?,
        val discounted_price: String?,
        val discounted_price_set: DiscountedPriceSet?,
        val price: String?,
        val price_set: PriceSet?,
        val requested_fulfillment_service_id: String?,
        val source: String?,
        val tax_lines: List<Any?>?,
        val title: String?
    ) {
        data class DiscountedPriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }

        data class PriceSet(
            val presentment_money: PresentmentMoney?,
            val shop_money: ShopMoney?
        ) {
            data class PresentmentMoney(
                val amount: String?,
                val currency_code: String?
            )

            data class ShopMoney(
                val amount: String?,
                val currency_code: String?
            )
        }
    }

    data class SubtotalPriceSet(
        val presentment_money: PresentmentMoney?,
        val shop_money: ShopMoney?
    ) {
        data class PresentmentMoney(
            val amount: String?,
            val currency_code: String?
        )

        data class ShopMoney(
            val amount: String?,
            val currency_code: String?
        )
    }

    data class TaxLine(
        val channel_liable: Boolean?,
        val price: Double?,
        val rate: Double?,
        val title: String?
    )

    data class TotalDiscountsSet(
        val presentment_money: PresentmentMoney?,
        val shop_money: ShopMoney?
    ) {
        data class PresentmentMoney(
            val amount: String?,
            val currency_code: String?
        )

        data class ShopMoney(
            val amount: String?,
            val currency_code: String?
        )
    }

    data class TotalLineItemsPriceSet(
        val presentment_money: PresentmentMoney?,
        val shop_money: ShopMoney?
    ) {
        data class PresentmentMoney(
            val amount: String?,
            val currency_code: String?
        )

        data class ShopMoney(
            val amount: String?,
            val currency_code: String?
        )
    }

    data class TotalPriceSet(
        val presentment_money: PresentmentMoney?,
        val shop_money: ShopMoney?
    ) {
        data class PresentmentMoney(
            val amount: String?,
            val currency_code: String?
        )

        data class ShopMoney(
            val amount: String?,
            val currency_code: String?
        )
    }

    data class TotalShippingPriceSet(
        val presentment_money: PresentmentMoney?,
        val shop_money: ShopMoney?
    ) {
        data class PresentmentMoney(
            val amount: String?,
            val currency_code: String?
        )

        data class ShopMoney(
            val amount: String?,
            val currency_code: String?
        )
    }

    data class TotalTaxSet(
        val presentment_money: PresentmentMoney?,
        val shop_money: ShopMoney?
    ) {
        data class PresentmentMoney(
            val amount: String?,
            val currency_code: String?
        )

        data class ShopMoney(
            val amount: String?,
            val currency_code: String?
        )
    }
}