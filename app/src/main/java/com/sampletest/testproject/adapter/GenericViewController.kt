package com.sampletest.testproject.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.Px
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.mikepenz.fastadapter.IClickable
import com.mikepenz.fastadapter.IExpandable
import com.mikepenz.fastadapter.ISubItem
import com.mikepenz.fastadapter.items.AbstractItem
import com.mikepenz.fastadapter.listeners.OnClickListener
import com.sampletest.testproject.R
import java.io.Serializable
import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap

/**
 * Created by kevingozali on 01/30/18.
 */
class GenericViewController<V : View?> :
    AbstractItem<GenericViewController<V>, GenericViewHolder<V>>,
    IExpandable<GenericViewController<*>, GenericViewController<*>>,
    ISubItem<GenericViewController<*>, GenericViewController<*>?>,
    IClickable<GenericViewController<V>>,
    Serializable
{
    private var mExpanded = false
    private var type: Int? = null
    private var mParent: GenericViewController<*>? = null
    private var mSubItems: List<GenericViewController<*>>? = null
    private var generator: GenericViewGenerator<V>? = null
    private var binder: GenericViewBinder<V>? = null
    private var unbinder: GenericViewBinder<V>? = null
    private var typedTags: MutableMap<Class<*>, Any>? = null

    /**
     * This enum used to determine which side to add stroke
     *
     *
     * 1. NO_STROKE : View item will not render stroke in its side
     *
     *
     * 2. SQUARE : View item will render stroke in top, left and right side
     *
     *
     * 3. TOP : View item will render stroke in top, left and right side
     *
     *
     * 4. SIDES : View item will render stroke in left and right side
     *
     *
     * 5. BOTTOM : View item will render stroke in bottom, left and right side
     */
    enum class StrokeType {
        NO_STROKE, SQUARE, TOP, SIDES, BOTTOM
    }

    private var strokeType = StrokeType.NO_STROKE
    /**
     * Color to replace current background of View item
     */
    @ColorRes
    private var strokeBackground = DEFAULT_STROKE_BACKGROUND_COLOR
    @ColorRes
    private var strokeColor = DEFAULT_STROKE_COLOR
    @Px
    private var strokeWidth = DEFAULT_STROKE_WIDTH
    private var strokeCornerRadius = 0f
    private var showStroke: Function0<Boolean>? = null
    private var mNormalDrawable: Drawable? = null
    /**
     * View to rotate when the item is clicked, perfect for expandable animation
     */
    @IdRes
    private var idViewToRotate = 0
    private var rotation = 0
    private var onItemClickListener: OnClickListener<GenericViewController<V>>? = null
    private val defaultOnItemClickListener = OnClickListener<GenericViewController<V>> { v, adapter, item, position ->
        v?.let {
            val viewToRotate = getViewToRotate(v)
            if (viewToRotate != null && rotation != 0) {
                if (item.isExpanded) {
                    ViewCompat.animate(viewToRotate).rotation(rotation.toFloat()).start()
                } else {
                    ViewCompat.animate(viewToRotate).rotation(0f).start()
                }
                return@OnClickListener onItemClickListener == null || onItemClickListener?.onClick(v, adapter, item, position) == true
            }
            onItemClickListener != null && onItemClickListener?.onClick(v, adapter, item, position) == true
        } ?: false
    }

    /**
     * @param type      must be globally unique
     * @param generator
     */
    constructor(type: Int, generator: GenericViewGenerator<V>) {
        this.generator = generator
        this.type = type
    }

    /**
     * Build the view item with globally unique type id
     *
     * @param generator
     */
    constructor(generator: GenericViewGenerator<V>) {
        this.generator = generator
        type = generator.hashCode()
    }

    /**
     * @param binder
     * @return
     */
    fun withBinder(binder: GenericViewBinder<V>?): GenericViewController<V> {
        this.binder = binder
        return this
    }

    /**
     * @param unbinder
     * @return
     */
    fun withUnbinder(unbinder: GenericViewBinder<V>?): GenericViewController<V> {
        this.unbinder = unbinder
        return this
    }

    override fun withOnItemClickListener(onItemClickListener: OnClickListener<GenericViewController<V>>): GenericViewController<V> {
        this.onItemClickListener = onItemClickListener
        return this
    }

    fun withViewToRotate(@IdRes id: Int): GenericViewController<V> {
        idViewToRotate = id
        return this
    }

    fun withRotation(rotation: Int): GenericViewController<V> {
        this.rotation = rotation
        return this
    }

    fun withStroke(strokeType: StrokeType, showStroke: Function0<Boolean>?): GenericViewController<V> {
        return withStroke(strokeType, DEFAULT_STROKE_BACKGROUND_COLOR, DEFAULT_STROKE_COLOR,
            DEFAULT_STROKE_WIDTH, 0f, showStroke)
    }

    fun withStroke(strokeType: StrokeType, @ColorRes replaceBackgroundColor: Int, showStroke: Function0<Boolean>?): GenericViewController<V> {
        return withStroke(strokeType, replaceBackgroundColor, DEFAULT_STROKE_COLOR,
            DEFAULT_STROKE_WIDTH, 0f, showStroke)
    }

    fun withStroke(strokeType: StrokeType, @ColorRes replaceBackgroundColor: Int,
                   @ColorRes strokeColor: Int, showStroke: Function0<Boolean>?): GenericViewController<V> {
        return withStroke(strokeType, replaceBackgroundColor, strokeColor,
            DEFAULT_STROKE_WIDTH, 0f, showStroke)
    }

    fun withStroke(strokeType: StrokeType, @ColorRes replaceBackgroundColor: Int,
                   @ColorRes strokeColor: Int, @Px strokeWidth: Int, showStroke: Function0<Boolean>?): GenericViewController<V> {
        return withStroke(strokeType, replaceBackgroundColor, strokeColor, strokeWidth, 0f, showStroke)
    }

    fun withStroke(strokeType: StrokeType, @ColorRes replaceBackgroundColor: Int,
                   @ColorRes strokeColor: Int, @Px strokeWidth: Int, strokeCornerRadius: Float,
                   showStroke: Function0<Boolean>?): GenericViewController<V> {
        this.strokeType = strokeType
        this.showStroke = showStroke
        strokeBackground = replaceBackgroundColor
        this.strokeColor = strokeColor
        this.strokeWidth = strokeWidth
        this.strokeCornerRadius = strokeCornerRadius
        return this
    }

    override fun getOnItemClickListener(): OnClickListener<GenericViewController<V>> {
        return defaultOnItemClickListener
    }

    /**
     * Do not use.
     * @return
     */
    override fun getLayoutRes(): Int {
        return 0 //this is ignored, we're overriding generateView instead
    }

    /**
     * The type of view item. it is globally unique
     * @return
     */
    override fun getType(): Int {
        return type ?: 0
    }

    /**
     * The logic to bind our data to the view
     */
    override fun bindView(holder: GenericViewHolder<V>, payloads: List<Any>) {
        super.bindView(holder, payloads)
        if (binder != null) {
            binder?.bindView(holder.view, this)
            mNormalDrawable = holder.view?.background
            bindStroke(holder.view as View)
        }
        val viewToRotate = getViewToRotate(holder.view as View)
        if (rotation != 0 && isExpanded) {
            viewToRotate?.rotation = rotation.toFloat()
        } else {
            viewToRotate?.rotation = 0f
        }
    }

    /**
     * set the identifier of this item
     *
     * @param identifier
     * @return
     */
    fun withIdentifier(identifier: String): GenericViewController<V> {
        mIdentifier = identifier.hashCode().toLong()
        return this
    }

    /**
     * This optional state unbinds the view to the default state
     *
     * @param holder
     */
    override fun unbindView(holder: GenericViewHolder<V>) {
        super.unbindView(holder)
        unbindStroke(holder.view as View)
        if (unbinder != null) {
            unbinder?.bindView(holder.view, this)
        }
        val viewToRotate = getViewToRotate(holder.view)
        viewToRotate?.clearAnimation()
    }

    private fun bindStroke(view: View) {
        if (strokeType != StrokeType.NO_STROKE) {
            if (showStroke != null && showStroke?.invoke() == true) {
                val paddingLeft = view.paddingLeft
                val paddingTop = view.paddingTop
                val paddingRight = view.paddingRight
                val paddingBottom = view.paddingBottom
                view.background = getDrawableStroke(view)
                view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
            } else {
                view.background = mNormalDrawable
            }
        }
    }

    private fun unbindStroke(view: View) {
        if (strokeType != StrokeType.NO_STROKE) {
            view.background = mNormalDrawable
            mNormalDrawable = null
        }
    }

    private fun getDrawableStroke(view: View): LayerDrawable {
        val layer1 = buildStrokeDrawable(view.context, strokeColor)
        val layer2 = buildStrokeDrawable(view.context, strokeBackground)
        val layerDrawable = LayerDrawable(arrayOf<Drawable>(layer1, layer2))
        val corners = FloatArray(8)
        Arrays.fill(corners, 0f)
        when (strokeType) {
            StrokeType.TOP -> {
                Arrays.fill(corners, 0, 4, strokeCornerRadius)
                layer1.cornerRadii = corners
                layer2.cornerRadii = corners
                layerDrawable.setLayerInset(1, strokeWidth, strokeWidth, strokeWidth, 0)
            }
            StrokeType.SIDES -> {
                layer1.cornerRadius = 0f
                layer2.cornerRadius = 0f
                layerDrawable.setLayerInset(1, strokeWidth, 0, strokeWidth, 0)
            }
            StrokeType.BOTTOM -> {
                Arrays.fill(corners, 4, 8, strokeCornerRadius)
                layer1.cornerRadii = corners
                layer2.cornerRadii = corners
                layerDrawable.setLayerInset(1, strokeWidth, 0, strokeWidth, strokeWidth)
            }
            StrokeType.SQUARE -> {
                layer1.cornerRadius = strokeCornerRadius
                layer2.cornerRadius = strokeCornerRadius
                layerDrawable.setLayerInset(1, strokeWidth, strokeWidth, strokeWidth, strokeWidth)
            }
        }
        return layerDrawable
    }

    private fun buildStrokeDrawable(context: Context, @ColorRes color: Int): GradientDrawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setColor(ContextCompat.getColor(context, color))
        drawable.mutate()
        return drawable
    }

    /**
     * Generates the view for which this view item
     * @param ctx
     * @param parent
     * @return
     */
    override fun generateView(ctx: Context, parent: ViewGroup): View {
        return generator?.generateView(ctx, parent) as View
    }

    /**
     * @param parent
     * @return
     */
    @Deprecated("Generate a view holder. do not use this")
    override fun getViewHolder(parent: ViewGroup): GenericViewHolder<V> {
        return getViewHolder(generateView(parent.context, parent))
    }

    /**
     * @param v
     * @return
     */
    @Deprecated("Generate a view holder. do not use this")
    override fun getViewHolder(v: View): GenericViewHolder<V> {
        return GenericViewHolder(v)
    }

    private fun getViewToRotate(parent: View): View? {
        return parent.findViewById(idViewToRotate)
    }

    /**
     * Is the View expanded or not. All view items are assumed to be expandable
     * @return
     */
    override fun isExpanded(): Boolean {
        return mExpanded
    }

    /**
     * Set the view item as expanded or not. The view will change after the recyclerview is notified or the view is recycled
     * @param collapsed
     * @return
     */
    override fun withIsExpanded(collapsed: Boolean): GenericViewController<V> {
        mExpanded = collapsed
        return this
    }

    /**
     * Set the view's subitems. The view will change after the recyclerview is notified or the view is recycled
     * @param subItems
     * @return
     */
    override fun withSubItems(subItems: List<GenericViewController<*>>): GenericViewController<V> {
        mSubItems = subItems
        for (subItem in subItems) {
            subItem.withParent(this)
        }
        return this
    }

    /**
     * It's exactly like withSubItems, but this one support variadic parameters for better visual
     * @param items
     * @return
     */
    fun withSubItemsOf(vararg items: GenericViewController<*>?): GenericViewController<V> {
        return withSubItems(items.asList().requireNoNulls())
    }

    /**
     * get the view's subitems
     * @return
     */
    override fun getSubItems(): List<GenericViewController<*>>? {
        return mSubItems
    }

    /**
     * this is always true.
     * @return
     */
    override fun isAutoExpanding(): Boolean {
        return true
    }

    /**
     * get the parent of this view if it is a subitem. a top level item would return null.
     * @return
     */
    override fun getParent(): GenericViewController<*>? {
        return mParent
    }

    /**
     * @param parent
     * @return
     */
    @Deprecated(" Do not use this. use the parent's withSubItems instead.\n" + "      ")
    override fun withParent(parent: GenericViewController<*>?): GenericViewController<*> {
        mParent = parent
        return this
    }

    /**
     * Add tag of arbitrary type. This object can contain multiple tags as long as each key (class)
     * is different. Tags set with this method will not collide with one that's set with
     * [.withTag].
     *
     * @return this object, for chaining
     */
    fun <T> withTag(key: Class<T>, value: T): GenericViewController<V> {
        if (typedTags == null) { // lazy creation
            synchronized(this) {
                // thread-safe
                if (typedTags == null) { // there's a small chance it's been set
                    typedTags = HashMap(2)
                }
            }
        }
        typedTags!![key] = value as Any
        return this
    }

    /**
     * Get the tag set by [.withTag] or null if not exists. Note that the key
     * (class) must exactly the same, a class is considered different to its superclass.
     */
    fun <T> getTag(key: Class<T>?): T? {
        return if (typedTags == null) {
            null
        } else typedTags!![key as Any] as T?
    }

    companion object {
        private const val DEFAULT_STROKE_WIDTH = 2
        private const val DEFAULT_STROKE_BACKGROUND_COLOR = R.color.colorPrimary
        private const val DEFAULT_STROKE_COLOR = R.color.colorPrimary
        /**
         * Utility fuction to create the list of view items in a declarative way. It also filters out nulls from the output
         *
         * @param items
         * @return
         */
        fun list(vararg items: AbstractItem<*, *>?): List<AbstractItem<*, *>> {
            val list = ArrayList<AbstractItem<*, *>>(items.size)
            for (i in items) {
                if (i == null) continue
                list.add(i)
            }
            return list
        }

        /**
         * Utility fuction to create the list of view items in a declarative way. It also filters out nulls from the output
         *
         * @param items
         * @return
         */
        @SafeVarargs
        fun list(vararg items: MutableList<AbstractItem<*, *>>?): MutableList<AbstractItem<*, *>> {
            val list: MutableList<AbstractItem<*, *>> = mutableListOf()
            for (i in items) {
                if (i == null) continue
                list.addAll(i)
            }
            return list
        }
    }
}