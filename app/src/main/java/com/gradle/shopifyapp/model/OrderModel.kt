package com.gradle.shopifyapp.model

data class OrderModel(
    val app_id: Long?,
    val billing_address: BillingAddress?,
    val browser_ip: String?,
    val buyer_accepts_marketing: Boolean?,
    val cancel_reason: String?,
    val cancelled_at: Any?,
    val cart_token: String?,
    val checkout_token: String?,
    val client_details: ClientDetails?,
    val closed_at: String?,
    val created_at: String?,
    val currency: String?,
    val current_subtotal_price: String?,
    val current_subtotal_price_set: CurrentSubtotalPriceSet?,
    val current_total_discounts: String?,
    val current_total_discounts_set: CurrentTotalDiscountsSet?,
    val current_total_duties_set: CurrentTotalDutiesSet?,
    val current_total_price: String?,
    val current_total_price_set: CurrentTotalPriceSet?,
    val current_total_tax: String?,
    val current_total_tax_set: CurrentTotalTaxSet?,
    val customer: Customer?,
    val customer_locale: String?,
    val discount_applications: List<DiscountApplication>?,
    val discount_codes: List<DiscountCode?>?,
    val email: String?,
    val estimated_taxes: Boolean?,
    val financial_status: String?,
    val fulfillment_status: String?,
    val fulfillments: List<Fulfillment?>?,
    val gateway: String?,
    val id: Long?,
    val landing_site: String?,
    val line_items: List<LineItem?>?,
    val location_id: Int?,
    val name: String?,
    val note: String?,
    val note_attributes: List<NoteAttribute?>?,
    val number: Int?,
    val order_number: Int?,
    val order_status_url: String?,
    val original_total_duties_set: OriginalTotalDutiesSet?,
    val payment_details: PaymentDetails?,
    val payment_gateway_names: List<String?>?,
    val payment_terms: PaymentTerms?,
    val phone: String?,
    val presentment_currency: String?,
    val processed_at: String?,
    val processing_method: String?,
    val referring_site: String?,
    val refunds: List<Refund?>?,
    val shipping_address: ShippingAddress?,
    val shipping_lines: List<ShippingLine?>?,
    val source_identifier: String?,
    val source_name: String?,
    val source_url: String?,
    val subtotal_price: Int?,
    val subtotal_price_set: SubtotalPriceSet?,
    val tags: String?,
    val tax_lines: List<TaxLine?>?,
    val taxes_included: Boolean?,
    val test: Boolean?,
    val token: String?,
    val total_discounts: String?,
    val total_discounts_set: TotalDiscountsSet?,
    val total_line_items_price: String?,
    val total_line_items_price_set: TotalLineItemsPriceSet?,
    val total_outstanding: String?,
    val total_price: String?,
    val total_price_set: TotalPriceSet?,
    val total_shipping_price_set: TotalShippingPriceSet?,
    val total_tax: String?,
    val total_tax_set: TotalTaxSet?,
    val total_tip_received: String?,
    val total_weight: Int?,
    val updated_at: String?,
    val user_id: Int?
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
        val code: String?,
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
        val discount_allocations: List<DiscountAllocation?>?,
        val duties: List<Duty?>?,
        val fulfillable_quantity: Int?,
        val fulfillment_service: String?,
        val fulfillment_status: String?,
        val gift_card: Boolean?,
        val grams: Int?,
        val id: Long?,
        val name: String?,
        val origin_location: OriginLocation?,
        val price: String?,
        val price_set: PriceSet?,
        val product_id: Long?,
        val properties: List<Property?>?,
        val quantity: Int?,
        val requires_shipping: Boolean?,
        val sku: String?,
        val tax_lines: List<TaxLine?>?,
        val taxable: Boolean?,
        val title: String?,
        val total_discount: String?,
        val total_discount_set: TotalDiscountSet?,
        val variant_id: Long?,
        val variant_title: String?,
        val vendor: String?
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