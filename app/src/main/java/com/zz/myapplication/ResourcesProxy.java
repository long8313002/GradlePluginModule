package com.zz.myapplication;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class ResourcesProxy extends Resources {
    private Resources proxy;

    /**
     * Create a new Resources object on top of an existing set of assets in an
     * AssetManager.
     *
     * @param assets  Previously created AssetManager.
     * @param metrics Current display metrics to consider when
     *                selecting/computing resource values.
     * @param config  Desired device configuration to consider when
     * @deprecated Resources should not be constructed by apps.
     * See {@link Context#createConfigurationContext(Configuration)}.
     */
    public ResourcesProxy(AssetManager assets, DisplayMetrics metrics, Configuration config) {
        super(assets, metrics, config);
    }

    public ResourcesProxy(Resources base,Resources proxy){
        this(base.getAssets(),base.getDisplayMetrics(),base.getConfiguration());
        this.proxy = proxy;
    }

    @Override
    public CharSequence getText(int id) throws NotFoundException {
        try{
            return proxy.getText(id);
        }catch (Exception e){
            return super.getText(id);
        }
    }

    @Override
    public Typeface getFont(int id) throws NotFoundException {
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return proxy.getFont(id);
            }
        }catch (Exception ignored){

        }
        return super.getFont(id);
    }

    @Override
    public CharSequence getQuantityText(int id, int quantity) throws NotFoundException {
        try{
            return proxy.getQuantityText(id,quantity);
        }catch (Exception e){
            return super.getQuantityText(id, quantity);
        }
    }

    @Override
    public String getString(int id) throws NotFoundException {
        try{
            return proxy.getString(id);
        }catch (Exception e){
            return super.getString(id);
        }
    }

    @Override
    public String getString(int id, Object... formatArgs) throws NotFoundException {
        try{
            return proxy.getString(id,formatArgs);
        }catch (Exception e){

        }
        return super.getString(id, formatArgs);
    }

    @Override
    public String getQuantityString(int id, int quantity, Object... formatArgs) throws NotFoundException {
        try{
            return proxy.getQuantityString(id,quantity,formatArgs);
        }catch (Exception e){

        }
        return super.getQuantityString(id, quantity, formatArgs);
    }

    @Override
    public String getQuantityString(int id, int quantity) throws NotFoundException {
        try{
            return proxy.getQuantityString(id,quantity);
        }catch (Exception e){
            return super.getQuantityString(id, quantity);
        }
    }

    @Override
    public CharSequence getText(int id, CharSequence def) {
        try{
            return proxy.getText(id,def);
        }catch (Exception e){
            return super.getText(id, def);
        }
    }

    @Override
    public CharSequence[] getTextArray(int id) throws NotFoundException {
        try{
            return proxy.getTextArray(id);
        }catch (Exception e){
            return super.getTextArray(id);
        }
    }

    @Override
    public String[] getStringArray(int id) throws NotFoundException {
        try{
            return proxy.getStringArray(id);
        }catch (Exception e){
            return super.getStringArray(id);
        }
    }

    @Override
    public int[] getIntArray(int id) throws NotFoundException {
        try{
            return proxy.getIntArray(id);
        }catch (Exception e){
            return super.getIntArray(id);
        }
    }

    @Override
    public TypedArray obtainTypedArray(int id) throws NotFoundException {
        try{
            return proxy.obtainTypedArray(id);
        }catch (Exception e){
            return super.obtainTypedArray(id);
        }
    }

    @Override
    public float getDimension(int id) throws NotFoundException {
        try{
            return proxy.getDimension(id);
        }catch (Exception e){
            return super.getDimension(id);
        }
    }

    @Override
    public int getDimensionPixelOffset(int id) throws NotFoundException {
        try{
            return proxy.getDimensionPixelOffset(id);
        }catch (Exception e){
            return super.getDimensionPixelOffset(id);
        }
    }

    @Override
    public int getDimensionPixelSize(int id) throws NotFoundException {
        try{
            return proxy.getDimensionPixelSize(id);
        }catch (Exception e){
            return super.getDimensionPixelSize(id);
        }
    }

    @Override
    public float getFraction(int id, int base, int pbase) {
        try{
            return proxy.getFraction(id,base,pbase);
        }catch (Exception e){
            return super.getFraction(id, base, pbase);
        }
    }

    @Override
    public Drawable getDrawable(int id) throws NotFoundException {
        try{
            return proxy.getDrawable(id);
        }catch (Exception e){
            return super.getDrawable(id);
        }
    }

    @Override
    public Drawable getDrawable(int id, Theme theme) throws NotFoundException {
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return proxy.getDrawable(id,theme);
            }
        }catch (Exception ignored){

        }
        return super.getDrawable(id, theme);
    }

    @Override
    public Drawable getDrawableForDensity(int id, int density) throws NotFoundException {
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                return proxy.getDrawableForDensity(id,density);
            }
        }catch (Exception ignored){

        }
        return super.getDrawableForDensity(id, density);
    }

    @Override
    public Drawable getDrawableForDensity(int id, int density, Theme theme) {
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return proxy.getDrawableForDensity(id,density,theme);
            }
        }catch (Exception ignored){

        }
        return super.getDrawableForDensity(id, density, theme);
    }

    @Override
    public Movie getMovie(int id) throws NotFoundException {
        try{
            return proxy.getMovie(id);
        }catch (Exception ignored){

        }
        return super.getMovie(id);
    }

    @Override
    public int getColor(int id) throws NotFoundException {
        try{
            return proxy.getColor(id);
        }catch (Exception ignored){

        }
        return super.getColor(id);
    }

    @Override
    public int getColor(int id, Theme theme) throws NotFoundException {
        try{
            return proxy.getColor(id);
        }catch (Exception ignored){

        }
        return super.getColor(id, theme);
    }

    @Override
    public ColorStateList getColorStateList(int id) throws NotFoundException {
        try{
            return proxy.getColorStateList(id);
        }catch (Exception ignored){

        }
        return super.getColorStateList(id);
    }

    @Override
    public ColorStateList getColorStateList(int id, Theme theme) throws NotFoundException {
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return proxy.getColorStateList(id,theme);
            }
        }catch (Exception ignored){

        }
        return super.getColorStateList(id, theme);
    }

    @Override
    public boolean getBoolean(int id) throws NotFoundException {
        try{
            return proxy.getBoolean(id);
        }catch (Exception ignored){

        }
        return super.getBoolean(id);
    }

    @Override
    public int getInteger(int id) throws NotFoundException {
        try{
            return proxy.getInteger(id);
        }catch (Exception ignored){

        }
        return super.getInteger(id);
    }

    @Override
    public XmlResourceParser getLayout(int id) throws NotFoundException {
        try{
            return proxy.getLayout(id);
        }catch (Exception ignored){

        }
        return super.getLayout(id);
    }

    @Override
    public XmlResourceParser getAnimation(int id) throws NotFoundException {
        try{
            return proxy.getAnimation(id);
        }catch (Exception ignored){

        }
        return super.getAnimation(id);
    }

    @Override
    public XmlResourceParser getXml(int id) throws NotFoundException {
        try{
            return proxy.getXml(id);
        }catch (Exception ignored){

        }
        return super.getXml(id);
    }

    @Override
    public InputStream openRawResource(int id) throws NotFoundException {
        try{
            return proxy.openRawResource(id);
        }catch (Exception ignored){

        }
        return super.openRawResource(id);
    }

    @Override
    public InputStream openRawResource(int id, TypedValue value) throws NotFoundException {
        try{
            return proxy.openRawResource(id);
        }catch (Exception ignored){

        }
        return super.openRawResource(id, value);
    }

    @Override
    public AssetFileDescriptor openRawResourceFd(int id) throws NotFoundException {
        try{
            return proxy.openRawResourceFd(id);
        }catch (Exception ignored){

        }
        return super.openRawResourceFd(id);
    }

    @Override
    public void getValue(int id, TypedValue outValue, boolean resolveRefs) throws NotFoundException {
        try{
             proxy.getValue(id,outValue,resolveRefs);
        }catch (Exception e){
            super.getValue(id, outValue, resolveRefs);
        }
    }

    @Override
    public void getValueForDensity(int id, int density, TypedValue outValue, boolean resolveRefs) throws NotFoundException {


        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                proxy.getValueForDensity(id, density, outValue, resolveRefs);
            }
        }catch (Exception e){
            super.getValueForDensity(id, density, outValue, resolveRefs);
        }
    }

    @Override
    public void getValue(String name, TypedValue outValue, boolean resolveRefs) throws NotFoundException {
        try{
            proxy.getValue(name, outValue, resolveRefs);
        }catch (Exception e){
            super.getValue(name, outValue, resolveRefs);
        }


    }

    @Override
    public TypedArray obtainAttributes(AttributeSet set, int[] attrs) {


        try{
            return proxy.obtainAttributes(set, attrs);
        }catch (Exception e){
            return super.obtainAttributes(set, attrs);
        }
    }

    @Override
    public void updateConfiguration(Configuration config, DisplayMetrics metrics) {


        try{
            proxy.updateConfiguration(config, metrics);
        }catch (Exception e){
            super.updateConfiguration(config, metrics);
        }
    }

    @Override
    public DisplayMetrics getDisplayMetrics() {
        try{
            return proxy.getDisplayMetrics();
        }catch (Exception e){
            return super.getDisplayMetrics();
        }
    }

    @Override
    public Configuration getConfiguration() {
        try{
            return proxy.getConfiguration();
        }catch (Exception e){
            return super.getConfiguration();
        }
    }

    @Override
    public int getIdentifier(String name, String defType, String defPackage) {


        try{
            return proxy.getIdentifier(name, defType, defPackage);
        }catch (Exception e){
            return super.getIdentifier(name, defType, defPackage);
        }
    }

    @Override
    public String getResourceName(int resid) throws NotFoundException {


        try{
            return proxy.getResourceName(resid);
        }catch (Exception e){
            return super.getResourceName(resid);
        }
    }

    @Override
    public String getResourcePackageName(int resid) throws NotFoundException {


        try{
            return proxy.getResourcePackageName(resid);
        }catch (Exception e){
            return super.getResourcePackageName(resid);
        }
    }

    @Override
    public String getResourceTypeName(int resid) throws NotFoundException {

        try{
            return proxy.getResourceTypeName(resid);
        }catch (Exception e){
            return super.getResourceTypeName(resid);
        }
    }

    @Override
    public String getResourceEntryName(int resid) throws NotFoundException {


        try{
            return proxy.getResourceEntryName(resid);
        }catch (Exception e){
            return super.getResourceEntryName(resid);
        }
    }

    @Override
    public void parseBundleExtras(XmlResourceParser parser, Bundle outBundle) throws IOException, XmlPullParserException {


        try{
            proxy.parseBundleExtras(parser, outBundle);
        }catch (Exception e){
            super.parseBundleExtras(parser, outBundle);
        }
    }

    @Override
    public void parseBundleExtra(String tagName, AttributeSet attrs, Bundle outBundle) throws XmlPullParserException {


        try{
            proxy.parseBundleExtra(tagName, attrs, outBundle);
        }catch (Exception e){
            super.parseBundleExtra(tagName, attrs, outBundle);
        }
    }
}
