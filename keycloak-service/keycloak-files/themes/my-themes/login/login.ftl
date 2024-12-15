<#import "template.ftl" as layout>
<@layout.registrationLayout displayMessage=!messagesPerField.existsError('username', 'password', 'phone') displayInfo=realm.password && realm.registrationAllowed && !registrationDisabled??; section>
    
    <#if section == "header">
        <title>${msg("Login Title", "ورود - فروش سیم کارت")}</title>
        <link href="https://fonts.googleapis.com/css2?family=Vazirmatn&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="${url.resourcesPath}/css/style.css">
        
    <#elseif section == "form">
        <div class="container">
            <div class="register-box">
                <h2>${msg("Welcome EliteConnect", "به الیت کانکت خوش آمدید!")}</h2>
                <p>${msg("Enter Info For login", "لطفاً اطلاعات خود را برای ورود وارد کنید")}</p>

                <form id="kc-form-registration" action="${url.registrationAction}" method="post">

                    <div class="input-group">
                        <label for="username">${msg("Phone", "شماره تلفن")}</label>
                        <input type="text" id="username" name="username" placeholder="${msg("enterPhone", "شماره تلفن همراه خود را وارد کنید")}" 
                               aria-invalid="<#if messagesPerField.existsError('username')>true</#if>">
                        <#if messagesPerField.existsError('username')>
                            <span class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
                                ${kcSanitize(messagesPerField.getFirstError('username'))?no_esc}
                            </span>
                        </#if>
                    </div>
 
                    <div class="input-group">
                        <label for="password">${msg("Password", "رمز عبور")}</label>
                        <input type="password" id="password" name="password" placeholder="${msg("enterPassword", "رمز عبور خود را وارد کنید")}" 
                               aria-invalid="<#if messagesPerField.existsError('password')>true</#if>">
                        <#if messagesPerField.existsError('password')>
                            <span class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
                                ${kcSanitize(messagesPerField.getFirstError('password'))?no_esc}
                            </span>
                        </#if>
                    </div>
                    
                   <div class="${properties.kcFormGroupClass!} ${properties.kcFormSettingClass!}">
                        <div id="kc-form-options">
                            <#if realm.rememberMe && !usernameHidden??>
                                <div class="checkbox">
                                    <label>
                                        <#if login.rememberMe??>
                                            <input tabindex="5" id="rememberMe" name="rememberMe" type="checkbox" checked> ${msg("rememberMe")}
                                        <#else>
                                            <input tabindex="5" id="rememberMe" name="rememberMe" type="checkbox"> ${msg("rememberMe")}
                                        </#if>
                                    </label>
                                </div>
                            </#if>
                            </div>
                            <div class="${properties.kcFormOptionsWrapperClass!}">
                                <#if realm.resetPasswordAllowed>
                                    <span><a tabindex="6" href="${url.loginResetCredentialsUrl}">${msg("doForgotPassword")}</a></span>
                                </#if>
                            </div>

                      </div>
                    
                    <button type="submit" class="register-button">${msg("Get otp", "دریافت رمز یک بار مصرف")}</button>
                </form>
                <div class="register-link">
                    <span>${msg("noAccount", "حساب کاربری ندارید؟")} <a href="${url.registrationUrl}">${msg("doRegister", "ثبت نام")}</a></span>
                </div>
            </div>
            <div class="image-box">
                <img src="${url.resourcesPath}/img/login.jpg" alt="${msg("Poster Alt", "پوستر")}">
            </div>
        </div>

    <#elseif section == "info">
        <#if realm.registrationAllowed && !registrationDisabled??>
            <div id="kc-registration-container">
                <div id="kc-registration">
                    <span>${msg("noAccount", "حساب کاربری ندارید؟")} <a href="${url.registrationUrl}">${msg("doRegister", "ثبت نام")}</a></span>
                </div>
            </div>
        </#if>
    <#elseif section == "socialProviders">
        <#if social?? && social.providers?has_content>
            <div id="kc-social-providers" class="${properties.kcFormSocialAccountSectionClass!}">
                <hr/>
                <h2>${msg("identity-provider-login-label", "ورود با شناسه هویتی مورد اعتماد")}</h2>
                <ul class="${properties.kcFormSocialAccountListClass!}">
                    <#list social.providers as p>
                        <li>
                            <a id="social-${p.alias}" href="${p.loginUrl}" class="${properties.kcFormSocialAccountListButtonClass!}">
                                <#if p.iconClasses?has_content>
                                    <i class="${p.iconClasses}" aria-hidden="true"></i>
                                </#if>
                                ${p.displayName!}
                            </a>
                        </li>
                    </#list>
                </ul>
            </div>
        </#if>
    </#if>

</@layout.registrationLayout>

